/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 26/nov/2013
 * Copyright 2013 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.pdfsam.ui.selection;

import java.io.File;
import java.util.Comparator;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;

import org.pdfsam.context.DefaultI18nContext;

/**
 * Definition of the {@link File} columns of the selection table
 * 
 * @author Andrea Vacondio
 * 
 */
enum FileColumn implements SelectionTableColumn<File> {

    NAME {

        public String getColumnTitle() {
            return DefaultI18nContext.getInstance().i18n("Name");
        }

        @Override
        public ObservableValue<File> getObservableValue(SelectionTableRowData data) {
            return new ReadOnlyObjectWrapper<>(data.getDocumentDescriptor().getFile());
        }

        @Override
        public String getTextValue(File item) {
            return (item != null) ? item.getName() : "";
        }

        @Override
        public Callback<TableColumn<SelectionTableRowData, File>, TableCell<SelectionTableRowData, File>> cellFactory() {
            return new Callback<TableColumn<SelectionTableRowData, File>, TableCell<SelectionTableRowData, File>>() {
                public TableCell<SelectionTableRowData, File> call(TableColumn<SelectionTableRowData, File> param) {
                    return new TableCell<SelectionTableRowData, File>() {
                        @Override
                        public void updateItem(final File item, boolean empty) {
                            super.updateItem(item, empty);
                            setText(getTextValue(item));
                            if (item != null) {
                                setTooltip(new Tooltip(item.getAbsolutePath()));
                            } else {
                                setTooltip(null);
                            }
                        }
                    };
                }
            };
        }

        public Comparator<File> comparator() {
            return new Comparator<File>() {
                public int compare(File o1, File o2) {
                    // TODO null safe?
                    return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                }
            };
        }
    }
}
