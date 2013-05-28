package org.nx.pkg4;

import java.util.List;

import org.nx.pkg4.node.NxNode;

public interface NxContainer {
  public List<NxNode> getNodes();
  public NxNode getRootNode();
}
