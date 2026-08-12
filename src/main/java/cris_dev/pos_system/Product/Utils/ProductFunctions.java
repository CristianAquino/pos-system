package cris_dev.pos_system.Product.Utils;

import lombok.NoArgsConstructor;

import java.text.Normalizer;

@NoArgsConstructor
public class ProductFunctions {
    
    public static String slugProduct(String productName) {
        return productName.replaceAll(
                "[`~!@#$%^&*()_\\-+=\\[\\]{};:'\"\\\\|/,.<>?\\s]",
                " ").trim().replaceAll(
                "\\s+",
                "-").toLowerCase();
    }

    public static String searchName(String productName) {
        return Normalizer.normalize(
                        productName,
                        Normalizer.Form.NFD).replaceAll(
                        "\\p{M}",
                        "")     // quita tildes
                .replaceAll(
                        "[^a-zA-Z0-9]",
                        "") // quita espacios y símbolos
                .toLowerCase();
    }
}