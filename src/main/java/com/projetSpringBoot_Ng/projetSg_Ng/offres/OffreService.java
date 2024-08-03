package com.projetSpringBoot_Ng.projetSg_Ng.offres;
import com.projetSpringBoot_Ng.projetSg_Ng.user.User;
import com.projetSpringBoot_Ng.projetSg_Ng.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OffreService {
    private final OffreRepository offreRepository;
    private final UserRepository userRepository;
    private final OffreMapper offreMapper;

   // check the github repo for refactor
    public Optional<Offre> findById(Integer id) {
        return offreRepository.findById(id);
    }


    public Integer save(OffreRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Offre offre = offreMapper.toOffre(request);
        offre.setRecruteur(user);
        return offreRepository.save(offre).getId();
    }
}
