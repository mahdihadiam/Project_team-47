package view.menu.offsMenu;

import view.command.BackCommand;
import view.command.HelpCommand;
import view.menu.Menu;
import view.menu.allProductsMenu.commands.FilteringCommand;
import view.menu.allProductsMenu.commands.SortingCommand;
import view.menu.allProductsMenu.subMenus.filteringMenu.FilteringMenu;
import view.menu.allProductsMenu.subMenus.sortingMenu.SortingMenu;
import view.menu.offsMenu.commands.ShowProductInOffCommand;
import view.menu.productMenu.ProductMenu;

public class AllOffsMenu extends Menu {
    public AllOffsMenu(Menu previousMenu) {
        super(previousMenu);
        setName("all offs menu");
    }


    @Override
    protected void setSubMenus() {
        subMenus.add(new ProductMenu(this));
        subMenus.add(new FilteringMenu(this, "offs"));
        subMenus.add(new SortingMenu(this, "offs"));
    }

    @Override
    protected void addCommands() {
        menuCommands.add(new ShowProductInOffCommand(this));
        menuCommands.add(new FilteringCommand(this, "offs"));
        menuCommands.add(new SortingCommand(this, "offs"));
        menuCommands.add(new HelpCommand(this));
        menuCommands.add(new BackCommand(this));
    }


}
