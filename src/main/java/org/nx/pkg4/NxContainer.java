package org.nx.pkg4;

import org.nx.pkg4.node.NxNode;

public interface NxContainer {
  public NxNode getNode(int id);
  public NxNode getRootNode();
}
