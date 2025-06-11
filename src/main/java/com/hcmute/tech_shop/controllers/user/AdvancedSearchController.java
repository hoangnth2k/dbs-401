package com.hcmute.tech_shop.controllers.user;

import com.hcmute.tech_shop.entities.HiddenFlag;
import com.hcmute.tech_shop.services.HiddenFlagService;
import com.hcmute.tech_shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/advanced-search")
@RequiredArgsConstructor
public class AdvancedSearchController {
    private final JdbcTemplate jdbcTemplate;
    private final ProductService productService;
    private final HiddenFlagService hiddenFlagService;

    @GetMapping
    public String advancedSearch(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sort,
            Model model) {
        
        if (query != null && !query.isEmpty()) {
            // Vulnerable SQL query with multiple layers of protection to bypass
            String sql = "SELECT p.*, b.name as brand_name, c.name as category_name " +
                    "FROM products p " +
                    "LEFT JOIN brands b ON p.brand_id = b.id " +
                    "LEFT JOIN categories c ON p.category_id = c.id " +
                    "WHERE p.active = true " +
                    "AND p.name LIKE CONCAT('%', ?, '%') " +
                    "AND (? IS NULL OR b.name = ?) " +
                    "AND (? IS NULL OR c.name = ?) " +
                    "ORDER BY " +
                    "CASE WHEN ? = 'price_asc' THEN p.price END ASC, " +
                    "CASE WHEN ? = 'price_desc' THEN p.price END DESC, " +
                    "CASE WHEN ? = 'name_asc' THEN p.name END ASC, " +
                    "CASE WHEN ? = 'name_desc' THEN p.name END DESC, " +
                    "p.id DESC";

            // Execute query with parameters
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sql,
                    query, brand, brand, category, category,
                    sort, sort, sort, sort);

            // Check for SQL injection attempt
            if (query.toLowerCase().contains("union") || 
                query.toLowerCase().contains("select") ||
                query.toLowerCase().contains("from") ||
                query.toLowerCase().contains("where") ||
                query.toLowerCase().contains("--") ||
                query.toLowerCase().contains("/*") ||
                query.toLowerCase().contains("*/") ||
                query.toLowerCase().contains("'") ||
                query.toLowerCase().contains("\"")) {
                // If SQL injection detected, return error
                model.addAttribute("error", "Invalid search query");
                return "user/advanced-search";
            }

            // If query contains special pattern, return flag
            if (query.contains("' OR '1'='1") && 
                query.contains("UNION") && 
                query.contains("SELECT") && 
                query.contains("_hidden_flags")) {
                HiddenFlag flag = hiddenFlagService.getFlagByChallenge("sql_injection");
                model.addAttribute("flag", flag.getFlag());
            }

            model.addAttribute("products", results);
        }

        return "user/advanced-search";
    }
} 