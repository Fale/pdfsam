/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 13/dic/2011
 * Copyright 2011 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pdfsam.context;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Context related to the i18n of the application.
 * 
 * @author Andrea Vacondio
 * 
 */
public interface I18nContext {

    /**
     * 
     * @param input
     *            input string
     * @param values
     *            values for the string placeholders
     * @return the internationalized messages.
     */
    String i18n(String input, String... values);

    /**
     * @return the configured {@link Locale}
     */
    Locale getLocale();

    /**
     * @return the used {@link ResourceBundle}
     */
    ResourceBundle getResources();

}
