package org.nx.pkg4.node;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import org.nx.pkg4.NxContainer;

public abstract class NxNode<T> {
  private final long id;
  
  private long firstChildId;
  private int childrenCount;
  
  // Cached children
  private List<NxNode> children;
  
  private String name;
  
  private T value;

  private NxContainer container;
  
  public NxNode(NxContainer container, long id, String name, long firstChildId, int childrenCount, T value) {
    this.id = id;
    this.name = name;
    this.firstChildId = firstChildId;
    this.childrenCount = childrenCount;
    this.value = value;
    this.container = container;
  }
  
  public boolean hasChildren() {
    return childrenCount == 0;
  }
  
  public T getValue() {
    return value;
  }
  
  public List<NxNode> getChildren() {
    if(childrenCount == 0) {
      return new ArrayList<NxNode>();
    }
    if(children == null) {
      children = new ArrayList<NxNode>(childrenCount);
      for(int i = (int) firstChildId; i < firstChildId + childrenCount; i++) {
        children.add(container.getNodes().get(i));
      }
    }
    return children;
  }
}
