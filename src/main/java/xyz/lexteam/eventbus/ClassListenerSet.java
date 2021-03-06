/*
 * This file is part of EventBus, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015-2016, Lexteam <http://lexteam.jamierocks.uk/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package xyz.lexteam.eventbus;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents all the {@link IDedicatedListener}s in a class.
 *
 * @author Jamie Mansfield
 */
public class ClassListenerSet implements IEventBus {

    private Map<Class<?>, IDedicatedListener> listeners = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerListener(Object listener) {
        if (listener instanceof IDedicatedListener) {
            this.listeners.put(((IDedicatedListener) listener).getHandles(), (IDedicatedListener) listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void post(Object event) {
        if (this.listeners.get(event.getClass()) != null) {
            this.listeners.get(event.getClass()).process(event);
        }
    }
}
