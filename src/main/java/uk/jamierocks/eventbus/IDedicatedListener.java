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

/**
 * Represents a 'dedicated listener'.
 * This is a type of listener which listens only on one event.
 *
 * @param <T> this is the type of which the event is.
 * @author Jamie Mansfield
 */
public interface IDedicatedListener<T> {

    /**
     * Processes the given event.
     *
     * @param event the event.
     */
    void process(T event);

    /**
     * Gets the type that this 'dedicated listener' listens for.
     *
     * @return the type of event.
     */
    Class<T> getHandles();
}
