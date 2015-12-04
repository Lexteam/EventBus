/*
 * This file is part of EventBus, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015, Jamie Mansfield <https://github.com/jamierocks>
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Represents a listener handler.
 * This wraps around a single method annotated with {@link Listener}.
 *
 * @author Jamie Mansfield
 */
public class ListenerHandler implements IDedicatedListener {

    private final Object instance;
    private final Method method;

    public ListenerHandler(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    /**
     * Gets the instantiated class of which holds the method that needs to be invoked.
     * This is required to invoke the method.
     *
     * @return the instantiated class.
     */
    public Object getInstance() {
        return this.instance;
    }

    /**
     * Gets the method of which needs to be invoked.
     *
     * @return the method.
     */
    public Method getMethod() {
        return this.method;
    }

    /**
     * Invokes the given method, in the given instance.
     *
     * @param event the event to use while invoking.
     */
    @Override
    public void process(Object event) {
        if (this.getHandles().isAssignableFrom(event.getClass())) {
            try {
                this.method.invoke(this.instance, event);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class getHandles() {
        return this.method.getParameterTypes()[0];
    }
}
