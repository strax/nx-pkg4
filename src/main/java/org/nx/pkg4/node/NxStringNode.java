package org.nx.pkg4.node;

import org.nx.pkg4.NxContainer;

public class NxStringNode extends NxNode {
  private String value;

  public NxStringNode(NxContainer container, long id, String name, long firstChildId,
      int childrenCount, String value) {
    super(container, id, name, firstChildId, childrenCount);
    this.value = value;
  }
  
  public String getValue() {
    return value;
  }
}
