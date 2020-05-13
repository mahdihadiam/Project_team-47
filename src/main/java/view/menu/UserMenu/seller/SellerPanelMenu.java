package view.menu.UserMenu.seller;

import view.command.ExitCommand;
import view.command.HelpCommand;
import view.menu.Menu;
import view.menu.UserMenu.commands.GoToOffsMenuCommand;
import view.menu.UserMenu.commands.GoToProductsMenuCommand;
import view.menu.UserMenu.commands.ViewPersonalInfoCommand;
import view.menu.UserMenu.seller.commands.ManageProductsCommandsSeller;
import view.menu.UserMenu.seller.commands.ViewCompanyInformationCommand;
import view.menu.UserMenu.seller.commands.ViewSalesHistoryCommand;
import view.menu.allProductsMenu.AllProductsMenu;
import view.menu.offsMenu.AllOffsMenu;

public class SellerPanelMenu extends Menu {
    public SellerPanelMenu(Menu previousMenu) {
        super(previousMenu);
        setName("seller panel menu");
    }


    @Override
    protected void setSubMenus() {
        subMenus.add(new AllOffsMenu(this));
        subMenus.add(new AllProductsMenu(this));
    }

    @Override
    protected void addCommands() {
        menuCommands.add(new ViewPersonalInfoCommand(this));
        menuCommands.add(new GoToOffsMenuCommand(this));
        menuCommands.add(new GoToProductsMenuCommand(this));
        menuCommands.add(new ViewCompanyInformationCommand(this));
        menuCommands.add(new ViewSalesHistoryCommand(this));
        menuCommands.add(new ManageProductsCommandsSeller(this));
        menuCommands.add(new ExitCommand(this));
        menuCommands.add(new HelpCommand(this));
    }
}
