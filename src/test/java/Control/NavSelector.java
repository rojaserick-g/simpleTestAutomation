package Control;

import Constant.Navegador;

import static Constant.Navegador.*;

public class NavSelector {

    public static Navegador seleccionNavegador(String navegador) {

        if (navegador == null || navegador.trim().isEmpty()) {
            return Chrome;
        }

        switch (navegador.trim()) {

            case "Chrome":
                return Chrome;

            case "Edge":
                return Edge;

            case "Firefox":
                return Firefox;

            case "BrowserStack":
                return BrowserStack;

            default:
                return Chrome;
        }
    }
}