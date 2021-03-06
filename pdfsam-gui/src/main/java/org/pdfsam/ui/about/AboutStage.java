/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 21/ott/2013
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
package org.pdfsam.ui.about;

import java.util.Collection;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.pdfsam.context.DefaultI18nContext;
import org.pdfsam.ui.ClosePane;
import org.pdfsam.ui.HideOnEscapeHandler;
import org.pdfsam.ui.support.Style;

/**
 * About {@link Stage} displaying informations about PDFsam
 * 
 * @author Andrea Vacondio
 * 
 */
@Named
public class AboutStage extends Stage {
    @Inject
    private AboutPane aboutPane;
    @Inject
    private Collection<Image> logos;
    @Resource(name = "styles")
    private List<String> styles;

    @PostConstruct
    void init() {
        BorderPane containerPane = new BorderPane();
        containerPane.getStyleClass().addAll(Style.CONTAINER.css());
        containerPane.setCenter(aboutPane);
        containerPane.setBottom(new ClosePane());
        final Scene scene = new Scene(containerPane);
        scene.setOnKeyReleased(new HideOnEscapeHandler(this));
        scene.getStylesheets().addAll(styles);
        setScene(scene);
        setTitle(DefaultI18nContext.getInstance().i18n("About"));
        getIcons().addAll(logos);
    }
}
