/**
 * Copyright (c) 2008, SnakeYAML
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.yaml.snakeyaml.immutable;

import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Arrays;
import javax.swing.border.MatteBorder;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

public class ImmutablesRepresenter extends Representer {

  public ImmutablesRepresenter() {
    super(new DumperOptions());
    this.representers.put(java.awt.Color.class, new RepresentColor());
    this.representers.put(Insets.class, new RepresentInsets());
    this.representers.put(MatteBorder.class, new RepresentMatteBorder());
    this.representers.put(Rectangle.class, new RepresentRectangle());
  }

  class RepresentInsets implements Represent {

    public Node representData(Object data) {
      Insets insets = (Insets) data;
      return representSequence(getTag(data.getClass(), new Tag(data.getClass())),
          Arrays.asList(insets.top, insets.left, insets.bottom, insets.right),
          DumperOptions.FlowStyle.FLOW);
    }

  }

  class RepresentRectangle implements Represent {

    public Node representData(Object data) {
      Rectangle rect = (Rectangle) data;
      return representSequence(getTag(data.getClass(), new Tag(data.getClass())),
          Arrays.asList(rect.x, rect.y, rect.width, rect.height), DumperOptions.FlowStyle.FLOW);
    }

  }

  class RepresentMatteBorder implements Represent {

    public Node representData(Object data) {
      MatteBorder mb = (MatteBorder) data;
      return representSequence(getTag(data.getClass(), new Tag(data.getClass())),
          Arrays.asList(mb.getBorderInsets(), mb.getMatteColor()), DumperOptions.FlowStyle.FLOW);
    }

  }

  class RepresentColor implements Represent {

    public Node representData(Object data) {
      java.awt.Color color = (java.awt.Color) data;
      return representSequence(getTag(data.getClass(), new Tag(data.getClass())),
          Arrays.asList(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()),
          DumperOptions.FlowStyle.FLOW);
    }

  }
}
