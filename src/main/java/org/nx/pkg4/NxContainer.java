package org.nx.pkg4;

import org.nx.pkg4.node.NxNode;

public interface NxContainer {
  public NxNode node(int id);
  public NxNode node(String path);
  public NxNode root();
}
