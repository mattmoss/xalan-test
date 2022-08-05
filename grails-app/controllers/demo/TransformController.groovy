package demo

class TransformController {

    TransformService transformService

    def index() {
        render '<pre>' + transformService.birds() + '</pre>'
    }

}
