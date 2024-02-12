package com.vaquitamu.view;

import com.vaquitamu.controller.DairyProductType;
import com.vaquitamu.controller.LotShipmentController;
import com.vaquitamu.utilities.InputLibrary;

import java.util.Date;

/**
 * This is a simple Terminal User interface
 * it allows to use LotShipmentController methods
 */
public class View {
    private final LotShipmentController controller;
    private String id, name;
    private Date expiration;
    private double special, content;
    private int units;
    private boolean addState;
    private final InputLibrary input;

    /**
     * The constructor instances all private variables
     */

    public View() {
        this.controller = new LotShipmentController();
        this.input = new InputLibrary();
    }

    /**
     * AddLot allows to add a Lot in controller to
     * calc its shortest path later.
     */
    public void addLot(){
        String result = "";
        String specialMessage = "";
        String contentMessage = "";
        double floorS = 0, floorC = 0;
        double topS = 0, topC = 0;
        DairyProductType productType = null;

        int type = input.inputInt("Ingrese tipo de lote a enviar:\n1. Leche\n2. Queso\n3. Yogourt\n", "Opción inválida. Intente de nuevo", 1, 3);

        this.id = input.inputId("Ingrese Id del lote (código numérico)\n", "Id inválido. Intente de nuevo");
        this.name = input.inputString("Ingrese nombre del producto lote: \n", "Nombre inválido. Intente de nuevo");
        this.expiration = input.inputDate("Ingrese fecha de caducidad del lote (dd-mm-yyyy): \n", "dd-MM-yyyy", "Fecha inválida. Intente de nuevo", "La fecha ingresada es de un producto caducado :(" );
        this.units = input.inputInt("Ingrese unidades a enviar [ 100 - 300]\n", "Valor inválido. Intente de nuevo", 100, 300);
        switch ( type ){
          case  1 -> {
                specialMessage = "Ingrese la cantidad de grasas (gr) [2gr - 3.5gr] \n";
                contentMessage = "Ingrese la cantidad de leche por bottella (L)[1L - 2.5L] \n";
                floorS = 2;
                topS = 3.5;
                floorC = 1;
                topC = 2.5;
                productType = DairyProductType.MILK;
          }
          case 2 -> {
                specialMessage = "Ingrese la cantidad de proteina (gr) [2gr - 5.5gr] \n";
                contentMessage = "Ingrese el peso por unidad de queso (Kg) [0.5kg - 2.5kg] \n";
                floorS = 2;
                topS = 5.5;
                floorC = 0.5;
                topC = 2.5;
                productType = DairyProductType.CHEESE;
          }
          case 3 -> {
              specialMessage = "Ingrese la cantidad de azucar (gr) [ 5gr - 20gr] \n";
              contentMessage = "Ingrese la cantidad de yogourt por envase (ml) [ 50ml - 80ml] \n";
              floorS = 5;
              topS = 20;
              floorC = 50;
              topC = 80;
              productType = DairyProductType.YOUGOURT;
          }
        }

        this.special = input.inputDouble(specialMessage, "Valor inválido. Intente de nuevo", floorS, topS);
        this.content = input.inputDouble( contentMessage, "Valor inválido. Intente de nuevo.", floorC, topC);

        addState =  this.controller.addLot(id, name, expiration, units, special, content, productType);
        result = addState ? "Lote añadido satisfactoriamente y listo para proceder a los datos de envío": "Error al agregar el lote. El lote ya es existente!!";
        System.out.println( result );
    }

    /**
     * This method allow to recollect the necessary
     * data to calc the shortest path of a previous
     * created Lot
     */
    public void calcRoute(){
        int townBeginIndex = 0;
        int townEndIndex = 0;
        String prompt = this.controller.showTowns();
        do {
            System.out.print("Ciudaes: \n" + prompt);
            townBeginIndex = input.inputInt("Ingrese ciudad inicial -> ", "Valor no válido, intente de nuevo", 1, this.controller.getGRAPH_SIZE());
            townEndIndex = input.inputInt("Ingrese ciudad destino -> ", "Valor no válido, intente de nuevo", 1, this.controller.getGRAPH_SIZE());
            if ( townBeginIndex == townEndIndex ) System.out.println("Las ciudades seleccionadas no pueden ser las mismas!. Intente de nuevo");
        }while ( townBeginIndex == townEndIndex);

        System.out.println("Ruta: " +  this.controller.calcRoute(townBeginIndex-1, townEndIndex-1));
    }

    /**
     * This method shows the Lots that its shortest path was calculated.
     * It doesn't show shipments in order.
     */

    public void showShipments(){
        System.out.println(this.controller.showPastShipments());
    }


    /**
     * This method is the main loop and allows interactivity with the user.
     */
    public void tui(){
        String menu = """
                                 ________________
                         ^__^   /                \\
                         (oo)   |    Vaquita Mu   |
                  /-------\\/ --'\\________________/
                 / |     ||
                *  ||W---||
                Opciones:
                1. Crear Lote
                2. Ver envios pasados 
                0. Salir ->   """;

        String exitMessage = """
                      (__)
                    /   oo      ______
                   |  /\\_|     |      \\
                   |  |___     |  N.S  |
                   |   ---@    |_______|
                *  |  |   ----   |    |
                 \\ |  |_____
                  \\|________|
                  Vaquita Mu.
                  Terminando sesión ..
                 """;
        int option = 1;
        do {
            option = input.inputInt(menu, "Opción no válida", 0, 2);
            switch ( option ){
                case 1 -> {
                    addLot();
                    if ( addState ){
                        calcRoute();
                    }
                }
                case 2 -> {
                    showShipments();
                }
                default -> {
                    System.out.println(exitMessage);
                }
            }
        }while ( option != 0);
    }
}
