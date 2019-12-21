package br.com.codenation.errordashboard.endpoints;


import br.com.codenation.errordashboard.domain.dto.FilterLogDTO;
import br.com.codenation.errordashboard.domain.dto.LogDTO;
import br.com.codenation.errordashboard.domain.entity.Log;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.service.impl.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/logs")
@Api(value = "logs")
public class LogController extends BaseController {

    @Autowired
    LogService logService;


    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Metódo de criação da Log")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucessfully retrieved list")
    })
    public ResponseEntity create(@RequestBody LogDTO log) {
        try {
            User user = getUser();
            return ResponseEntity.ok().body(logService.create(log, user));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Metódo de deleção da Log")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucessfully retrieved list")
    })
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            logService.delete(id);
            return ResponseEntity.ok().body("Log deletado com sucesso!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }


    @Transactional
    @GetMapping(value = "/filter")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Metódo de filtro da Log")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucessfully retrieved list")
    })
    public ResponseEntity filter(@RequestBody FilterLogDTO filter) {
        try {

            return ResponseEntity.ok().body(logService.filter(filter));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @Transactional
    @PutMapping(value = "/{id}/archive")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Metódo de arquivamento da Log")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucessfully archived log")
    })
    public ResponseEntity archive(@PathVariable("id") Long id) {
        try {
            logService.archive(id);
            return ResponseEntity.ok().body("Log arquivado com sucesso!");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @Transactional
    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Metódo de listagem da Log")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucessfully retrieved list")
    })
    public ResponseEntity getAll() {
        try {

            return ResponseEntity.ok().body(logService.getAll());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
