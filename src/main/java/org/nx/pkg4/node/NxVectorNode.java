package org.nx.pkg4.node;

import java.awt.Point;

import org.nx.pkg4.NxContainer;

public class NxVectorNode extends NxNode<Point> {
  public NxVectorNode(NxContainer container, long id, String name, long firstChildId,
      int childrenCount, Point value) {
    super(container, id, name, firstChildId, childrenCount, value);
  }
}
