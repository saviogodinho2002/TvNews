package godinho.savio.TvNews.controllers;

import godinho.savio.TvNews.Models.NewsPaper;
import godinho.savio.TvNews.dtos.NewsPaperRecordDto;
import godinho.savio.TvNews.repositories.NewsPaperRepository;
import godinho.savio.TvNews.services.NewsPaperService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class NewsPaperController {



    @Autowired
    NewsPaperRepository newsPaper;
    @Autowired
    private NewsPaperRepository newsPaperRepository;
    @Autowired
    private NewsPaperService newsPaperService;

    @PostMapping("/news_papers")
    public ResponseEntity<NewsPaper> store(@RequestBody @Valid NewsPaperRecordDto newsPaperDto){//validate
        NewsPaper newspaper = new NewsPaper(); //cria o model

        // copia da esquerda para a direta; dados -> model
        BeanUtils.copyProperties(newsPaperDto,newspaper);//o model ainda n foi criado no db

        return ResponseEntity.status(HttpStatus.CREATED).body(newsPaper.save(newspaper)); //aqui ele salvo no db e enviado
    }
    @GetMapping("/news_papers")
    public ResponseEntity<List<NewsPaper>> getByDescription(@RequestParam(required = false) String description){
        List<NewsPaper> newsPaperList = newsPaperService.getByDescription(description);
        /*for (NewsPaper newsPaper : newsPaperList) {
            //faz um map e adiciona uma propriedade que é o link (pegando do proprio controller)
            //para o findByIdNoCaso
            UUID id = newsPaper.getId();
            newsPaper.add(
              WebMvcLinkBuilder.linkTo(
                      WebMvcLinkBuilder.methodOn(NewsPaperController.class).findById(id)
              ).withSelfRel().withName("urlToShow")

            );
            newsPaper.add(
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(NewsPaperController.class).destroy(id)
                    ).withSelfRel().withName("urlToDestroy")
            );
        }*/
        return ResponseEntity.status(HttpStatus.OK).body(newsPaperList);
    }
    @GetMapping("/news_papers/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id){
        Optional<NewsPaper> newsPaperOp = newsPaperRepository.findById(id);
        if(newsPaperOp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
        NewsPaper newsPaper = newsPaperOp.get();
        newsPaper.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(NewsPaperController.class).getByDescription("")
                ).withSelfRel().withName("urlToList")
        );
        return ResponseEntity.status(HttpStatus.OK).body(newsPaperOp.get());

    }

    @PutMapping("/news_papers/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid NewsPaperRecordDto newsPaperDto){
        Optional<NewsPaper> newsPaperOp = newsPaperRepository.findById(id);
        if(newsPaperOp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
        var newspaper = newsPaperOp.get();
        BeanUtils.copyProperties(newsPaperDto,newspaper);
        newspaper =  newsPaperRepository.save(newspaper);
        return ResponseEntity.status(HttpStatus.OK).body(newspaper);

    }
    @DeleteMapping( "/news_papers/{id}")
    public ResponseEntity<Object> destroy(@PathVariable UUID id){
        Optional<NewsPaper> newsPaperOp = newsPaperRepository.findById(id);
        if(newsPaperOp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
        var newspaper = newsPaperOp.get();
          newsPaperRepository.delete(newspaper);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");

    }

}
