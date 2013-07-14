package net.mindengine.galen.page;


import static java.lang.String.format;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Rect {

    private int left;
    private int width;
    private int top;
    private int height;

    public Rect(int left, int top, int width, int height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public Point[] getPoints() {
        return new Point[]{new Point(left, top), 
                new Point(left + width, top),
                new Point(left + width, top + height),
                new Point(left, top + height)};
    }

    public boolean contains(Point point) {
        return (point.getLeft() >= left && point.getLeft() <= (left + width) 
                && point.getTop() >= top && point.getTop() <= (top + height));
    }
    
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(left).append(top).append(width).append(height).toHashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Rect))
            return false;
        
        Rect rhs = (Rect)obj;
        return new EqualsBuilder().append(left, rhs.left).append(top, rhs.top).append(width, rhs.width).append(height, rhs.height).isEquals();
    }
    
    @Override
    public String toString() {
        return format("Rect(left: %d, top: %d, w: %d, h: %d)", left, top, width, height);
    }

}
