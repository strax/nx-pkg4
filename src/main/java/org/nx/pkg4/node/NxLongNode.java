package org.nx.pkg4.node;

import org.nx.pkg4.NxContainer;

public class NxLongNode extends NxNode {
  public NxLongNode(NxContainer container, long id, String name, long firstChildId, int childrenCount,
      long value) {
    super(container, id, name, firstChildId, childrenCount, value);
  }
  
  @Override
  public Long getValue() {
    return (Long) super.getValue();
  }
}
