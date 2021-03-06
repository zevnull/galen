/*******************************************************************************
* Copyright 2014 Ivan Shubin http://mindengine.net
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/
package net.mindengine.galen.validation.specs;

import net.mindengine.galen.page.Point;
import net.mindengine.galen.page.Rect;
import net.mindengine.galen.specs.Location;
import net.mindengine.galen.specs.Side;
import net.mindengine.galen.specs.SpecInside;
import net.mindengine.galen.validation.PageValidation;

public class SpecValidationInside extends SpecValidationGeneral<SpecInside> {

    @Override
    protected int getOffsetForSide(Rect mainArea, Rect parentArea, Side side, SpecInside spec) {
        if (side == Side.LEFT) {
            return mainArea.getLeft() - parentArea.getLeft();
        }
        else if (side == Side.TOP) {
            return mainArea.getTop() - parentArea.getTop();
        }
        else if (side == Side.RIGHT) {
            return parentArea.getLeft() + parentArea.getWidth() - (mainArea.getLeft() + mainArea.getWidth());
        }
        else if (side == Side.BOTTOM) {
            return parentArea.getTop() + parentArea.getHeight() - (mainArea.getTop() + mainArea.getHeight());
        }
        else {
            return 0;
        }
    }

    @Override
    protected String verifyLocation(Rect mainArea, Rect secondArea, Location location, PageValidation pageValidation, SpecInside spec) {
        if (!spec.getPartly()) {
            Point[] points = mainArea.getPoints();
            
            for (Point point : points) {
                if (!secondArea.contains(point)) {
                    return "not completely inside";
                }
            }
        }
        return super.verifyLocation(mainArea, secondArea, location, pageValidation, spec);
    }
}
