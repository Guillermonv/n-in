package com.n.in.controller;

import com.n.in.model.NEntity;
import com.n.in.service.NBackOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/n")
@RequiredArgsConstructor
public class BackOfficeController {

    private final NBackOfficeService nBackOfficeService;

    @GetMapping("/status")
    public List<NEntity> getByStatus(@RequestParam List<String> status) {
        return nBackOfficeService.findByStatuses(status);
    }


    @PutMapping("/update-status")
    public ResponseEntity<?> updateStatus(
            @RequestParam("id") Long id,
            @RequestParam("status") String status
    ) {
        boolean updated = nBackOfficeService.updateStatus(id, status);

        if (!updated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID no encontrado: " + id);
        }

        return ResponseEntity.ok("Status actualizado correctamente.");
    }
}
