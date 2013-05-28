package org.nx.pkg4.node;

import org.nx.pkg4.NxContainer;

public class NxDoubleNode extends NxNode<Double> {
  public NxDoubleNode(NxContainer container, long id, String name, long firstChildId,
      int childrenCount, Double value) {
    super(container, id, name, firstChildId, childrenCount, value);
  }
}
