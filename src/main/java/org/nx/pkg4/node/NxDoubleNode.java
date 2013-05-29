package org.nx.pkg4.node;

import org.nx.pkg4.NxContainer;

public class NxDoubleNode extends NxNode {
  private double value;

  public NxDoubleNode(NxContainer container, long id, String name, long firstChildId,
      int childrenCount, double value) {
    super(container, id, name, firstChildId, childrenCount);
    this.value = value;
  }
  
  public Double value() {
    return value;
  }
}
