package br.com.example.upafacil.ms_filtro_unidade_atendimento.domain.enums;

public enum StateCountry {
    ACRE(0),
    ALAGOAS(1),
    AMAPA(2),
    AMAZONAS(3),
    BAHIA(4),
    CEARA(5),
    DISTRITO_FEDERAL(6),
    ESPIRITO_SANTO(7),
    GOIAS(8),
    MARANHAO(9),
    MATO_GROSSO(10),
    MATO_GROSSO_DO_SUL(11),
    MINAS_GERAIS(12),
    PARA(13),
    PARAIBA(14),
    PARANA(15),
    PERNAMBUCO(16),
    PIAUI(17),
    RIO_DE_JANEIRO(18),
    RIO_GRANDE_DO_NORTE(19),
    RIO_GRANDE_DO_SUL(20),
    RONDONIA(21),
    RORAIMA(22),
    SANTA_CATARINA(23),
    SAO_PAULO(24),
    SERGIPE(25),
    TOCANTINS(26);

    private final int codigo;

    StateCountry(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}