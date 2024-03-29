package com.mca.infrastructure.commons;

public class Endpoints {
    private static final String ERROR_NO_INSTANCIABLE = "NO INSTANCIABLE";

    public class SagaVideoGame {
        public static final String GET_SAGAS_BY_GAME_ID = "/game/{gameId}/saga";

        private SagaVideoGame(){throw new IllegalStateException(ERROR_NO_INSTANCIABLE);}
    }
    public class SagaRelated{
        public static final String GET_SAGAS_RELATED_BY_SAGA_ID = "/game-saga/{sagaId}/related-sagas";
        private SagaRelated(){throw new IllegalStateException(ERROR_NO_INSTANCIABLE);}
    }
}
