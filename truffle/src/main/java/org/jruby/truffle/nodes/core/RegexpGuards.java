/*
 * Copyright (c) 2015 Oracle and/or its affiliates. All rights reserved. This
 * code is released under a tri EPL/GPL/LGPL license. You can use it,
 * redistribute it and/or modify it under the terms of the:
 *
 * Eclipse Public License version 1.0
 * GNU General Public License version 2
 * GNU Lesser General Public License version 2.1
 */

package org.jruby.truffle.nodes.core;

import org.jruby.truffle.runtime.core.RubyBasicObject;
import org.jruby.util.StringSupport;

public class RegexpGuards {

    public static boolean isInitialized(RubyBasicObject regexp) {
        return RegexpNodes.getRegex(regexp) != null;
    }

    public static boolean isRegexpLiteral(RubyBasicObject regexp) {
        return RegexpNodes.getOptions(regexp).isLiteral();
    }

    public static boolean isValidEncoding(RubyBasicObject string) {
        return StringNodes.scanForCodeRange(string) != StringSupport.CR_BROKEN;
    }

}
