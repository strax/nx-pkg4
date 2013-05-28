package org.nx.pkg4;

public class NxHeader {
  private final long nodeCount, stringCount, bitmapCount, audioCount;
  
  private final long nodeOffset, stringTableOffset, bitmapTableOffset, audioTableOffset;
  
  public NxHeader(long nodeCount, long nodeOffset, long stringCount,
      long stringTableOffset, long bitmapCount, long bitmapTableOffset,
      long audioCount, long audioTableOffset) {
    this.nodeCount = nodeCount;
    this.nodeOffset = nodeOffset;
    this.stringCount = stringCount;
    this.stringTableOffset = stringTableOffset;
    this.bitmapCount = bitmapCount;
    this.bitmapTableOffset = bitmapTableOffset;
    this.audioCount = audioCount;
    this.audioTableOffset = audioTableOffset;
  }

  public long getNodeCount() {
    return nodeCount;
  }

  public long getStringCount() {
    return stringCount;
  }

  public long getBitmapCount() {
    return bitmapCount;
  }

  public long getAudioCount() {
    return audioCount;
  }

  public long getNodeOffset() {
    return nodeOffset;
  }

  public long getStringTableOffset() {
    return stringTableOffset;
  }

  public long getBitmapTableOffset() {
    return bitmapTableOffset;
  }

  public long getAudioTableOffset() {
    return audioTableOffset;
  }
}
