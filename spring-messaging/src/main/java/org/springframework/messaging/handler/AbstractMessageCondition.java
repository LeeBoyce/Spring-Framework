/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.messaging.handler;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.lang.Nullable;

/**
 * Base class for {@code MessageCondition's} that pre-declares abstract methods
 * {@link #getContent()} and {@link #getToStringInfix()} in order to provide
 * implementations of {@link #equals(Object)}, {@link #hashCode()}, and
 * {@link #toString()}.
 *
 * @author Rossen Stoyanchev
 * @since 4.0
 */
public abstract class AbstractMessageCondition<T extends AbstractMessageCondition<T>>
		implements MessageCondition<T> {


	/**
	 * Return the collection of objects the message condition is composed of
	 * (e.g. destination patterns), never {@code null}.
	 */
	protected abstract Collection<?> getContent();

	/**
	 * The notation to use when printing discrete items of content.
	 * For example " || " for URL patterns or " && " for param expressions.
	 */
	protected abstract String getToStringInfix();


	@Override
	public boolean equals(@Nullable Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj != null && getClass() == obj.getClass()) {
			AbstractMessageCondition<?> other = (AbstractMessageCondition<?>) obj;
			return getContent().equals(other.getContent());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getContent().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		for (Iterator<?> iterator = getContent().iterator(); iterator.hasNext();) {
			Object expression = iterator.next();
			builder.append(expression.toString());
			if (iterator.hasNext()) {
				builder.append(getToStringInfix());
			}
		}
		builder.append("]");
		return builder.toString();
	}

}
