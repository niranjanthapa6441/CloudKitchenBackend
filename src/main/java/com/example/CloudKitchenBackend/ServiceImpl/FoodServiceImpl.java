package com.example.CloudKitchenBackend.ServiceImpl;

import com.example.CloudKitchenBackend.DTO.FoodDTO;
import com.example.CloudKitchenBackend.DTO.FoodListDTO;
import com.example.CloudKitchenBackend.Model.Food;
import com.example.CloudKitchenBackend.Repositories.CategoryRepo;
import com.example.CloudKitchenBackend.Repositories.FoodRepo;
import com.example.CloudKitchenBackend.Repositories.MenuFoodRepo;
import com.example.CloudKitchenBackend.Request.FoodRequest;
import com.example.CloudKitchenBackend.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepo repo;
    @Autowired
    private MenuFoodRepo menuFoodRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public FoodDTO save(FoodRequest request) {
        checkValidation(request);
        Food food= repo.save(toFood(request));
        return toFoodDTO(food);
    }

    @Override
    public String delete(int id) {
        repo.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public FoodListDTO findAll(String name, int page, int size) {
        List<Food> foods = new ArrayList<>();
        List<FoodDTO> foodDTOList = new ArrayList<>();
        Pageable paging= PageRequest.of(page, size);
        Page<Food> pageFoods = null;
        if (name == null)
            pageFoods= repo.findAll(paging);
        else if(name != null )
            pageFoods= repo.findByName(name,paging);
        foods = pageFoods.getContent();
        for (Food food:foods
             ) {
            foodDTOList.add(toFoodDTO(food));
        }
        FoodListDTO foodListDTO = toFoodDTO(foodDTOList,pageFoods.getNumber(),pageFoods.getTotalElements(),pageFoods.getTotalPages());
        return foodListDTO;
    }

    @Override
    public Food findById(int id) {
        Optional<Food> category= repo.findById(id);
        if (!category.isPresent()){
            throw new NullPointerException("Category Not Found");
        }
        return category.get();
    }

    @Override
    public FoodDTO update(FoodRequest request, int id) {
        Food findFood=findById(id);
        checkValidation(request);
        Food food=toFood(request);
        Food updateFood=food;
        updateFood.setId(findFood.getId());
        return toFoodDTO(repo.save(updateFood));
    }

    private Food toFood(FoodRequest request) {
        Food food= new Food();
        food.setName(request.getName());
        return food;
    }
    private FoodDTO toFoodDTO(Food food) {
        return FoodDTO.builder().
                name(food.getName()).
                build();
    }
    private FoodListDTO toFoodDTO(List<FoodDTO> foodDTOList, int number, long totalElements, int totalPages) {
        return FoodListDTO.builder().
                foods(foodDTOList).
                currentPage(number).
                totalPages(totalPages).
                totalElements(totalElements)
                .build();
    }
    private void checkValidation(FoodRequest request) {

    }
}
