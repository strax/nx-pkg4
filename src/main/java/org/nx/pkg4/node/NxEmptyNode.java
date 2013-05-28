package org.nx.pkg4.node;

import org.nx.pkg4.NxContainer;

public class NxEmptyNode extends NxNode {
  public NxEmptyNode(NxContainer container, long id, String name, long firstChildId, int childrenCount) {
    super(container, id, name, firstChildId, childrenCount);
  }
  
  public Object getValue() {
    return null;
  }
}
