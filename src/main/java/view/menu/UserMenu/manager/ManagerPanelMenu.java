package view.menu.UserMenu.manager;

import view.command.ExitCommand;
import view.command.HelpCommand;
import view.menu.Menu;
import view.menu.UserMenu.commands.GoToOffsMenuCommand;
import view.menu.UserMenu.commands.GoToProductsMenuCommand;
import view.menu.UserMenu.commands.LoginAndRegisterCommandForUser;
import view.menu.UserMenu.commands.ViewPersonalInfoCommand;
import view.menu.UserMenu.manager.Commands.*;
import view.menu.allProductsMenu.AllProductsMenu;
import view.menu.loginAndRegisterMenu.LoginAndRegisterMenu;
import view.menu.offsMenu.AllOffsMenu;

public class ManagerPanelMenu extends Menu {
    public ManagerPanelMenu(Menu previousMenu) {
        super(previousMenu);
        setName("manager panel menu");
    }

    @Override
    protected void setSubMenus() {
        subMenus.add(new AllOffsMenu( this));
        subMenus.add(new AllProductsMenu(this));
        subMenus.add(new LoginAndRegisterMenu(this));
    }

    @Override
    protected void addCommands() {
        menuCommands.add(new LoginAndRegisterCommandForUser(this));
        menuCommands.add(new GoToOffsMenuCommand(this));
        menuCommands.add(new GoToProductsMenuCommand(this));
        menuCommands.add(new ViewPersonalInfoCommand(this));
        menuCommands.add(new ManageUsersCommand(this));
        menuCommands.add(new ManageAllProductsCommand(this));
        menuCommands.add(new CreateDiscountCodesCommand(this));
        menuCommands.add(new ViewDiscountCodesCommand(this));
        menuCommands.add(new ManageRequestsCommand(this));
        menuCommands.add(new ManageCategoriesCommand(this));
        menuCommands.add(new HelpCommand(this));
        menuCommands.add(new ExitCommand(this));

    }
}
