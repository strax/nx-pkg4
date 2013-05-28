package org.nx.pkg4.node;

import org.nx.pkg4.NxContainer;

public class NxDoubleNode extends NxNode {
  public NxDoubleNode(NxContainer container, long id, String name, long firstChildId,
      int childrenCount, double value) {
    super(container, id, name, firstChildId, childrenCount, value);
  }
  
  @Override
  public Double getValue() {
    return (Double) super.getValue();
  }
}
