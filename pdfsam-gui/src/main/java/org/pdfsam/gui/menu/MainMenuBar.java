/*
 * Created on 05/apr/2012
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; 
 * either version 2 of the License.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program; 
 * if not, write to the Free Software Foundation, Inc., 
 *  59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package org.pdfsam.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.noos.xing.mydoggy.ContentManager;
import org.noos.xing.mydoggy.plaf.ui.util.SwingUtil;
import org.pdfsam.context.DefaultI18nContext;
import org.pdfsam.gui.AboutFrame;
import org.pdfsam.gui.AbstractContentPanel;
import org.pdfsam.gui.workspace.LoadWorkspaceAction;
import org.pdfsam.gui.workspace.SaveWorkspaceAction;

import static org.pdfsam.support.RequireUtils.require;

/**
 * Menu bar for the main frame
 * 
 * @author Andrea Vacondio
 * 
 */
public class MainMenuBar extends JMenuBar {

    private Map<MenuType, JMenu> menus = new HashMap<MenuType, JMenu>();

    public MainMenuBar() {
        JMenu menuFile = new JMenu();
        menuFile.setText(DefaultI18nContext.getInstance().getI18n().tr("File"));
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFile.add(new ExitAction());
        add(menuFile);

        JMenu menuEdit = new JMenu();
        menuEdit.setText(DefaultI18nContext.getInstance().getI18n().tr("Edit"));
        menuEdit.setMnemonic(KeyEvent.VK_E);
        add(menuEdit);

        JMenu menuWorkspace = new JMenu();
        menuWorkspace.setText(DefaultI18nContext.getInstance().getI18n().tr("Workspace"));
        menuWorkspace.setMnemonic(KeyEvent.VK_W);
        menuWorkspace.add(new LoadWorkspaceAction());
        menuWorkspace.add(new SaveWorkspaceAction());
        menuWorkspace.addSeparator();
        JMenu recentWorkspaces = new JMenu();
        recentWorkspaces.setText(DefaultI18nContext.getInstance().getI18n().tr("Recent"));
        menuWorkspace.add(recentWorkspaces);
        add(menuWorkspace);

        JMenu menuModules = new JMenu();
        menuModules.setText(DefaultI18nContext.getInstance().getI18n().tr("Modules"));
        menuModules.setMnemonic(KeyEvent.VK_M);
        add(menuModules);

        JMenu menuHelp = new JMenu();
        menuHelp.setText(DefaultI18nContext.getInstance().getI18n().tr("Help"));
        menuHelp.setMnemonic(KeyEvent.VK_H);
        menuHelp.add(new AboutAction());
        add(menuHelp);

        menus.put(MenuType.FILE, menuFile);
        menus.put(MenuType.HELP, menuHelp);
        menus.put(MenuType.WORKSPACE, menuWorkspace);
        menus.put(MenuType.EDIT, menuEdit);
        menus.put(MenuType.MODULES, menuModules);
    }

    /**
     * Adds a menuitem to the Help menu to show or hide a system content panel.
     * 
     * @param type
     *            the menu type
     * @param contentManager
     * @param panel
     */
    public void addSystemContentAction(MenuType type, ContentManager contentManager, AbstractContentPanel panel) {
        JMenu menu = menus.get(type);
        require(menu != null, "Unable to fine the given menu: " + type);
        menu.add(new SystemContentAction(contentManager, panel));
    }

    /**
     * Action used to display About panel
     * 
     * @author Andrea Vacondio
     * 
     */
    private class AboutAction extends AbstractAction {

        AboutAction() {
            super(DefaultI18nContext.getInstance().getI18n().tr("About"));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            AboutFrame aboutFrame = AboutFrame.getInstance();
            if (!aboutFrame.isVisible()) {
                SwingUtil.centrePositionOnScreen(aboutFrame);
                aboutFrame.setVisible(true);
            }
        }
    }

}