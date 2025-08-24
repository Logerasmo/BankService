# REST-сервис
### Запуск контейнера
docker-compose up --build
### Остановка контейнера
docker-compose down
## Используемый порт 5500
# Примеры запросов
## POST http:/localhost:5500/transfer
body json

          cardFromNumber:
            type: string
          cardFromValidTill:
            type: string
          cardFromCVV:
            type: string
          cardToNumber:
            type: string  
          amount:
            type: object
            properties:  
              value:
                type: integer
              currency:
                type: string

## POST http:/localhost:5500/confirmOperation
body json

          operationId:
            type: string
            description: Operation id
          code:
            type: string
            description: Verification code
