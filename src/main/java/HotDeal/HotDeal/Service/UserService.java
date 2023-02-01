package HotDeal.HotDeal.Service;

import HotDeal.HotDeal.Domain.Comment;
import HotDeal.HotDeal.Dto.GoodDto;
import HotDeal.HotDeal.Domain.User;
import HotDeal.HotDeal.Dto.WishListDto;
import HotDeal.HotDeal.Exception.*;
import HotDeal.HotDeal.Repository.UserRepository;
import HotDeal.HotDeal.Util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static HotDeal.HotDeal.Exception.Validator.validatePassword;


@Service
@RequiredArgsConstructor
public class UserService {
    private final Logger logger2 = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    public ResponseEntity<Map<String, Object>> userRegister(User user) {
        Map<String, Object> responseJson = new HashMap<>();
        validateDuplicateID(user.getId());
        validateDuplicateNickname(user.getNickname());
        userRepository.save(user);
        responseJson.put("result", user);
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> userLogin(User user) {  //user에는 id,password 밖에 없다.
        Map<String, Object> responseJson = new HashMap<>();
        User savedUser = userRepository.findById(user.getId())
                .orElseThrow(IdNotFoundException::new);
        validatePassword(user, savedUser);

        String jwtToken = JwtUtils.generateJwtToken(user);
        responseJson.put("token", jwtToken);
        String resultMessage = String.format("User with id '%s' joined with default login\n", savedUser.getId());
        responseJson.put("resultMessage", resultMessage);
        responseJson.put("object", savedUser);

        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }

    public ResponseEntity<Map<String, Object>> getUserProfileById(String userId) {
        //test를 위해 임의로 객체 값들을 임시로 넣어놓은 상태이다.
        Map<String, Object> userProfile = new HashMap<>();

        User tempUser = userRepository.findById(userId)
                .orElseThrow(IdNotFoundException::new);
        userProfile.put("nickname", tempUser.getNickname());
        userProfile.put("email", tempUser.getEmail());

        List<Comment> comments = tempUser.getComments();
        List<GoodDto> goods = tempUser.getGoods();

        userProfile.put("recommends", goods);
        userProfile.put("comments", comments);
        return ResponseEntity.status(HttpStatus.OK).body(userProfile);
    }

    public ResponseEntity<Map<String, Object>> getUserWishlistsById(String userId) {
        //test를 위해 임의로 객체 값들을 임시로 넣어놓은 상태이다.
        Map<String, Object> userWishlists = new HashMap<>();

        User tempUser = userRepository.findById(userId)
                .orElseThrow(IdNotFoundException::new);
        List<WishListDto> wishLists = tempUser.getWishLists();
        userWishlists.put("result", wishLists);

        return ResponseEntity.status(HttpStatus.OK).body(userWishlists);
    }

    public ResponseEntity<Map<String, Object>> changeNickname(String userId,Map<String,String> nickname){
        String[] forbiddenWords = {
                "건전한닉네임", "잦", "봊", "네다통","통구이","민주화","ㅁㅈㅎ","느금마","니애미","니어미","니엄마","니애비","느그애비","느그애미","애미터","애미뒤","앰뒤","앰창","갈보","강간","개같","개년","개뇬","개미친","개부랄","개불알","개빠구리","개뼉다구","개새","개색","개쌔끼","개자석","개자슥","개자식","지랄","꼴통","느그엄마","느검마","니기미","니미","대가리","대갈","대갈빡","대갈통","대굴빡","뒈진다","뒤질","등쉰","등신","딸따뤼","딸따리","딸딸","딸딸이","똘추","매춘","몸파는","발정","배때지","병신","보지","보짓","보털","부랄","부럴","불알","븅딱","븅삼","븅쉰","븅신","빙딱","빙삼","빙시","빙신","빠구리","빠구뤼","빠꾸리","빠꾸뤼","빠순이","빠큐","뻑큐","뽀르노","뽀오지","사까쉬","사까시","상노무","상놈","새1끼","새갸","새꺄","새뀌","새끼","새뤼","새리","새캬","새키","성감대","성경험","성관계","성교육","섹하고","섹하구","섹하자","섹하장","쉬빨","쉬뻘","쉬뿔","쉬파","쉬팍","쉬팔","쉬팡","쉬펄","쉬퐁","쉬풀","스너프","스댕","스뎅","스발","스벌","스와핑","스왑","스트립","스팔","스펄","슴가","싀발","싀밸","싀벌","싀벨","싀봉","싀팍","싀팔","싀펄","시1발","싸갈통","싸까시","싸이코","싸카시","쌍너엄","쌍넌","쌍넘","쌍녀언","쌍년","쌍놈","쌍뇬","쌍눔","쌍늠","썅넘","썅년","썅놈","썅뇬","썅눔","썅늠","써글","썩을넘","썩을년","썩을놈","썩을뇬","썩을눔","썩을늠","씌바","씌박","씌발","씌방","씌밸","씌벌","씌벙","씌벨","씌부랄","씌불","씌블","씌빌","씌빨","씌뻘","씌파","씌팍","씌팔","씌팡","씌펄","씌퐈","씌퐝","씨발","씨1발","씨팔","씹","아가리","아가지","아갈","아괄","아구리","아구지","아구창","아굴창","자쥐","자즤","조까","조옷","좆","주둥아리","주둥이","창녀","창년","후레","후장","2중대","3일한","SUCKSEX","가카","간민정음","간철수","갓치","갓카","강된장남","개독교","개쌍도","개쌍디언","게이","경상디언","고무통","고무현","골좁이","규재찡","근혜찡","김치남","김치녀","까보전","꼬춘쿠키","꿈떡마쇼","꿘","나랑께","낙태충","냄져","네다보","네다홍","盧","노무노무","노미넴","노부엉","노시계","노알라","노오란","노오랗","노운지","노짱","뇌물현","다이쥬","다카키마사오","땅크","로류","로린이","록또","머중","멍청도","메갈","명예자지","무현RT","미러링","박원숭","베충","베츙","보라니","보력지원","보밍","보빨","보슬","보테크","보혐","부랄발광","붸충","빵즈","빼애애액","빼애액","사타부언","새드","새부","설라디언","소라넷","숨쉴한","슨상","슨상님","씹선비","씹치","씹치남","알보칠","암베","앙망","애비충","엑윽","여혐","오유충","우덜식","우돌식","우흥","운지","욷높","웃흥","원조가카","유충","응딩이","일간베스트","일게이","일밍","일밍아웃","일베","일베충","일벤저스","일벤져스","일붸","자1지","자들자들","자릉내","자취냄","자혐","장애인","재기찡","재기하다","전땅크","전라디언","젠신병자","좌고라","좌빨","좌음","좌좀","주절먹","중력절","짱깨","쪽국","챙놈","청웅","코르셋","탈김치","탈라도","통궈","투명애비","틀딱","피떡갈비","핑좆남","한남","한남또","한남충","할아보지","해상방위대","핵대중","핵펭귄","행게이","허수애비","호뽑뽑요","혼외수","홍어","홍팍","운영자","운영인","관리자","관리인","주인장","sex","casino", "x지", "보x", "자x", "X지", "보X", "자X", "0지", "보0", "자0", "o지", "보o", "자o", "보1지", "자1지", "보2지", "자2지", "보3지", "자3지", "섹스", "섹x", "x스", "o스", "섹o", "랄부", "ㅗ", "시발", "凸"
        }; // 포함하면 안 되는 단어들 모음

        Map<String, Object> responseJson = new HashMap<>();

        User user = userRepository.findById(userId)
                .orElseThrow(IdNotFoundException::new);
        String newNickname = nickname.get("nickname");

        // 포함하면 안 되는 내용을 포함하는 경우
        for (String forbiddenWord: forbiddenWords) {
            if (newNickname.contains(forbiddenWord)) {
                responseJson.put("resultMessage", "금지 단어가 닉네임에 포함되었습니다");
                return ResponseEntity.status(HttpStatus.OK).body(responseJson);
            }
        }
        validateDuplicateNickname(newNickname);
        user.setNickname(newNickname);

        userRepository.save(user);
        responseJson.put("resultMessage", "닉네임이 업데이트 되었습니다. 새 닉네임은 " + newNickname + "입니다.");
        return ResponseEntity.status(HttpStatus.OK).body(responseJson);
    }
    private void validateDuplicateID(String id) {
        if (userRepository.existsById(id)) {
            throw new CustomException(ErrorCode.DUPLICATE_ID);
        }
    }
    private void validateDuplicateNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        }
    }
}
