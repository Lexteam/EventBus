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
package uk.jamierocks.eventbus;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A simple implementation of {@link IEventBus}.
 *
 * @author Jamie Mansfield
 */
public class SimpleEventBus implements IEventBus {

    private Map<Class, Set<IDedicatedListener>> handlers;

    public SimpleEventBus() {
        this.handlers = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerListener(Object listener) {
        if (listener instanceof IDedicatedListener) {
            IDedicatedListener dedicatedListener = (IDedicatedListener) listener;
            Set<IDedicatedListener> listeners =
                    this.handlers.getOrDefault(dedicatedListener.getHandles(), new HashSet<>());
            listeners.add(dedicatedListener);
            this.handlers.put(dedicatedListener.getHandles(), listeners);
        } else {
            for (Method m : listener.getClass().getMethods()) {
                if (m.getAnnotation(Listener.class) != null && m.getParameterCount() == 1) {
                    ListenerHandler handler = new ListenerHandler(listener, m);
                    Set<IDedicatedListener> listeners =
                            this.handlers.getOrDefault(handler.getHandles(), new HashSet<>());
                    listeners.add(handler);
                    this.handlers.put(handler.getHandles(), listeners);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void post(Object event) {
        for (IDedicatedListener listener : this.handlers.get(event.getClass())) {
            listener.process(event);
        }
    }
}
