package enums;

/**
 * Enumeração que representa as unidades de medida
 * utilizadas pelos produtos cadastrados no sistema.
 *
 * Os valores definidos permitem controlar o estoque
 * de acordo com a forma de comercialização ou armazenamento
 * de cada produto.
 *
 * @author Gabriel Conci
 * @version 1.0
 * @since 2026
 */
public enum TipoUnidade {

    /**
     * Unidade de medida em quilogramas.
     */
    KG,

    /**
     * Unidade de medida em litros.
     */
    LITRO,

    /**
     * Unidade de medida por unidade individual.
     */
    UNIDADE,

    /**
     * Unidade de medida por pacote.
     */
    PACOTE

}