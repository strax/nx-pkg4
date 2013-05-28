package org.nx.pkg4.node;

import org.nx.pkg4.NxContainer;

public class NxLongNode extends NxNode {
  private long value;

  public NxLongNode(NxContainer container, long id, String name, long firstChildId, int childrenCount,
      long value) {
    super(container, id, name, firstChildId, childrenCount);
    this.value = value;
  }
  
  public Long getValue() {
    return value;
  }
}
