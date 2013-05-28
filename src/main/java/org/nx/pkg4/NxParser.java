package org.nx.pkg4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.awt.Point;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.nx.pkg4.node.NxAudioNode;
import org.nx.pkg4.node.NxBitmapNode;
import org.nx.pkg4.node.NxDoubleNode;
import org.nx.pkg4.node.NxEmptyNode;
import org.nx.pkg4.node.NxLongNode;
import org.nx.pkg4.node.NxNode;
import org.nx.pkg4.node.NxStringNode;
import org.nx.pkg4.node.NxVectorNode;

public class NxParser implements NxContainer {
  private final static byte[] MAGIC = new byte[] {0x50, 0x4B, 0x47, 0x34};
  private final static Charset UTF8 = Charset.forName("UTF-8");
  
  private final static int TYPE_EMPTY = 0;
  private final static int TYPE_LONG = 1;
  private final static int TYPE_DOUBLE = 2;
  private final static int TYPE_STRING = 3;
  private final static int TYPE_VECTOR = 4;
  private final static int TYPE_BITMAP = 5;
  private final static int TYPE_AUDIO = 6;
  
  private final Path path;
  
  private NxHeader header;
  
  private List<NxNode> nodes;
  
  private List<String> strings;
  
  public NxParser(Path path) {
    this.path = path;
  }
  
  public NxParser parse() throws IOException {
    FileChannel file = FileChannel.open(path);
    MappedByteBuffer mapping = file.map(MapMode.READ_ONLY, 0, file.size());
    ByteBuf buf = Unpooled.wrappedBuffer(mapping).order(ByteOrder.LITTLE_ENDIAN);
    
    header = parseHeader(buf);
    strings = parseStrings(header, buf);
    nodes = parseNodes(header, buf);
    
    file.close();
    
    return this;
  }
  
  public List<NxNode> getNodes() {
    return nodes;
  }
  
  public NxNode getRootNode() {
    return nodes.get(0);
  }
  
  private NxHeader parseHeader(ByteBuf buf) throws IOException {
    byte[] magic = buf.readBytes(4).array();
    if(!Arrays.equals(magic, MAGIC)) {
      throw new IOException("Invalid file format");
    }
    
    return new NxHeader(
        buf.readUnsignedInt(),
        buf.readLong(),
        buf.readUnsignedInt(),
        buf.readLong(),
        buf.readUnsignedInt(),
        buf.readLong(),
        buf.readUnsignedInt(),
        buf.readLong());
  }
  
  private List<NxNode> parseNodes(NxHeader header, ByteBuf buf) {
    buf.readerIndex((int) header.getNodeOffset());
    
    int nodeCount = (int) header.getNodeCount();
    List<NxNode> nodes = new ArrayList<NxNode>(nodeCount);
    
    for(int i = 0; i < nodeCount; i++) {
      String name = strings.get((int) buf.readUnsignedInt());
      int firstChildId = (int) buf.readUnsignedInt();
      int childrenCount = buf.readUnsignedShort();
      int type = buf.readUnsignedShort();
      ByteBuf data = buf.readBytes(8);
      
      NxNode node;
      switch(type) {
      case TYPE_LONG:
        node = new NxLongNode(this, i, name, firstChildId, childrenCount, data.readLong());
        break;
      case TYPE_DOUBLE:
        node = new NxDoubleNode(this, i, name, firstChildId, childrenCount, data.readDouble());
        break;
      case TYPE_STRING:
        node = new NxStringNode(this, i, name, firstChildId, childrenCount, strings.get((int) data.readUnsignedInt()));
        break;
      case TYPE_VECTOR:
        node = new NxVectorNode(this, i, name, firstChildId, childrenCount, new Point(data.readInt(), data.readInt()));
        break;
      case TYPE_BITMAP:
        node = new NxBitmapNode(this, i, name, firstChildId, childrenCount);
        break;
      case TYPE_AUDIO:
        node = new NxAudioNode(this, i, name, firstChildId, childrenCount);
        break;
      case TYPE_EMPTY:
      default:
        node = new NxEmptyNode(this, i, name, firstChildId, childrenCount);
      }
      nodes.add(node);
    }
    return nodes;
  }
  
  private List<String> parseStrings(NxHeader header, ByteBuf buf) {
    buf.readerIndex((int) header.getStringTableOffset());

    int stringCount = (int) header.getStringCount();    
    List<String> strings = new ArrayList<String>(stringCount);
    
    for(int i = 0; i < stringCount; i++) {
      int offset = (int) buf.readLong();
      buf.markReaderIndex();
      buf.readerIndex(offset);
      
      String string = buf.readBytes(buf.readUnsignedShort()).toString(UTF8);
      strings.add(i, string);
      
      buf.resetReaderIndex();
    }
    
    return strings;
  }
}
