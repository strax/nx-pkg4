package org.nx.pkg4.node;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.nx.pkg4.NxContainer;

public abstract class NxNode {
  private final long id;
  
  private long firstChildId;
  private int childrenCount;
  
  private Map<String, NxNode> children;
  
  private String name;

  private NxContainer container;
  
  public NxNode(NxContainer container, long id, String name, long firstChildId, int childrenCount) {
    this.id = id;
    this.name = name;
    this.firstChildId = firstChildId;
    this.childrenCount = childrenCount;
    this.container = container;
  }
  
  public String getName() {
    return name;
  }
  
  public long getId() {
    return id;
  }
  
  public boolean hasChildren() {
    return childrenCount > 0;
  }
  
  public int getChildrenCount() {
    return childrenCount;
  }
  
  public abstract Object getValue();
  
  public NxNode getChild(String name) {
    if(childrenCount == 0) {
      return null;
    }
    if(children == null) {
      children = fetchChildren();
    }
    return children.get(name);
  }
  
  public Collection<NxNode> getChildren() {
    if(childrenCount == 0) {
      return Collections.emptyList();
    }
    if(children == null) {
      children = fetchChildren();
    }
    return children.values();
  }
  
  public String toString() {
    return name;
  }
  
  private Map<String, NxNode> fetchChildren() {
    Map<String, NxNode> children = new HashMap<>();
    for(int i = (int) firstChildId; i < firstChildId + childrenCount; i++) {
      NxNode node = container.getNode(i);
      children.put(node.getName(), node);
    }
    return children;
  }
}
