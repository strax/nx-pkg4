package org.nx.pkg4.node;

import java.awt.image.BufferedImage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import net.jpountz.lz4.LZ4Factory;

import org.nx.pkg4.NxContainer;

public class NxBitmapNode extends NxNode {
  private long offset;
  private ByteBuf buf;
  
  private BufferedImage value;
  private int width, height;

  public NxBitmapNode(NxContainer container, long id, String name, long firstChildId, int childrenCount, long offset, int width, int height, ByteBuf buf) {
    super(container, id, name, firstChildId, childrenCount);
    this.offset = offset;
    this.buf = buf;
    this.width = width;
    this.height = height;
  }
  
  public BufferedImage value() {
    if(value == null) {
      buf.readerIndex((int) offset);
      long length = buf.readUnsignedInt();
      
      byte[] compressed = new byte[(int) length];
      buf.readBytes(compressed);
      
      byte[] uncompressed = new byte[width * height * 4];
      LZ4Factory.fastestInstance().decompressor().decompress(compressed, 0, uncompressed, 0, uncompressed.length);
      
      value = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      ByteBuf data = Unpooled.wrappedBuffer(uncompressed);
      
      for(int y = 0; y < height; y++) {
        for(int x = 0; x < width; x++) {
          short b = data.readUnsignedByte();
          short g = data.readUnsignedByte();
          short r = data.readUnsignedByte();
          short a = data.readUnsignedByte();
          value.setRGB(x, y, (a << 24) | (r << 16) | (g << 8) | b);
        }
      }
    }
    return value;
  }
}
