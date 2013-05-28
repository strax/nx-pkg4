package org.nx.pkg4.node;

import org.nx.pkg4.NxContainer;

public class NxLongNode extends NxNode<Long> {
  public NxLongNode(NxContainer container, long id, String name, long firstChildId, int childrenCount,
      Long value) {
    super(container, id, name, firstChildId, childrenCount, value);
  }
}
