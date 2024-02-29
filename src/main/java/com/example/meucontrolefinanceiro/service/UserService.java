package com.example.meucontrolefinanceiro.service;

import com.example.meucontrolefinanceiro.model.User;
import com.example.meucontrolefinanceiro.model.dtos.UserRegistrationDTO;
import com.example.meucontrolefinanceiro.model.dtos.UserUpdateBudgetDTO;
import com.example.meucontrolefinanceiro.repository.CategoryRepository;
import com.example.meucontrolefinanceiro.repository.UserRepository;
import com.example.meucontrolefinanceiro.utils.exceptions.AccountNotDeletedException;
import com.example.meucontrolefinanceiro.utils.exceptions.AccountNotFound;
import com.example.meucontrolefinanceiro.utils.exceptions.ExistentAccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        checkForExistentAccountByAmazonId(userRegistrationDTO.getAmazonId());
        User userModel = new User();
        BeanUtils.copyProperties(userRegistrationDTO, userModel);
        return userRepository.save(userModel);
    }

    public User findUserByAmazonId(String amazonId) {
        return userRepository
                .findByAmazonId(amazonId)
                .orElseThrow(() -> new AccountNotFound("Conta não encontrada"));
    }

    public User findUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFound("Conta não encontrada"));
    }

    public void updateUserAmazonId(String oldAmazonId, String newAmazonId) {
        checkForExistentAccountByAmazonId(newAmazonId);
        User userModel = findUserByAmazonId(oldAmazonId);
        userModel.setAmazonId(newAmazonId);
        userRepository.save(userModel);
    }

    public void updateUserBudget(UserUpdateBudgetDTO userUpdateBudgetDTO) {
        User userModel = findUserByAmazonId(userUpdateBudgetDTO.getAmazonId());
        userModel.setBudget(userUpdateBudgetDTO.getNewBudget());
        userRepository.save(userModel);
    }

    public void deleteUserByAmazonId(String amazonId) {
        User userModel = findUserByAmazonId(amazonId);
        categoryRepository.deleteAllByUserAmazonId(amazonId);
        userRepository
                .deleteUserByAmazonId(userModel.getAmazonId())
                .orElseThrow(() -> new AccountNotDeletedException(
                    "Não possível deletar essa conta"
                ));
    }

    private void checkForExistentAccountByAmazonId(String amazonId) {
        if (userRepository.findByAmazonId(amazonId).isPresent()) {
            throw new ExistentAccountException("Conta existente");
        }
    }
}
