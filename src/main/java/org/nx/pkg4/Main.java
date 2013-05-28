package org.nx.pkg4;

import java.nio.file.Paths;
import java.util.Collection;

import org.nx.pkg4.node.NxNode;

public class Main {
  public static void main(String[] args) throws Exception {
    NxParser parser = new NxParser(Paths.get("Data.nx"));
    NxNode root = parser.parse().getRootNode();
    Collection<NxNode> mobs = root.getChild("Mob").getChildren();
  }
}
