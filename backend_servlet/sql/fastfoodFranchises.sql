drop table fastfood_franchises;
purge RECYCLEBIN;

create table fastfood_franchises(
    brand varchar2(50) primary key,
    company varchar2(50) not null,
    franchises_num number not null,
    avg_sales number not null,
    area_avg_sales number not null
);

insert into fastfood_franchises values( '버거킹(Burger King)', '(주)비케이알', 99, 913851, 12792 );
insert into fastfood_franchises values( '피제리아 나인로드', '(주)씨엠월드에프앤씨', 16, 675295, 5542 );
insert into fastfood_franchises values( '롯데리아', '롯데지알에스(주)', 1207, 647888, 12543 );
insert into fastfood_franchises values( '맘스터치', '해마로푸드서비스(주)', 1167, 425896, 17326 );
insert into fastfood_franchises values( '미스터톡톡(Mr TokTok)', '(주)두손커머스', 11, 403014, 80603 );
insert into fastfood_franchises values( '더제이케이키친박스', '(주)더제이케이푸드스토리', 11, 341414, 12644 );
insert into fastfood_franchises values( '자니로켓', '(주)신세계푸드', 7, 318249, 51948 );
insert into fastfood_franchises values( '파파이스', '(주)티에스푸드앤시스템', 54, 304692, 8598 );
insert into fastfood_franchises values( '밀플랜비', '(주)밀플랜비식품', 15, 277080, 11157 );
insert into fastfood_franchises values( '퀴즈노스서브', '(주)유썸', 68, 274592, 15154 );
insert into fastfood_franchises values( '버거307', '버거307', 13, 244373, 13806 );
insert into fastfood_franchises values( '도니버거', '(주)도니버거', 5, 203007, 8033 );
insert into fastfood_franchises values( '호봉토스트', '(주)호봉푸드시스템', 17, 186706, 21660 );
insert into fastfood_franchises values( '비스트로피자', '(주)글로비', 21, 182060, 13104 );
insert into fastfood_franchises values( '미스앤미스터포테이토', '(주)도연에프엔씨', 18, 162902, 12732 );
insert into fastfood_franchises values( '지정환피자', '(주)정담에프에스', 78, 146451, 9701 );
insert into fastfood_franchises values( '멜팅그릴', '(주)제이제이에프앤비', 10, 138481, 12902 );
insert into fastfood_franchises values( '웰덤치킨', '(주)푸디노에프앤디', 87, 137728, 869 );
insert into fastfood_franchises values( '마미쿡', '(주)훌랄라', 13, 133858, 9236 );
insert into fastfood_franchises values( '델리랩 (Delilab)', '델리랩,여우넷', 15, 115477, 12689 );
insert into fastfood_franchises values( '스테프핫도그', '(주)스테프코리아', 59, 112906, 10742 );
insert into fastfood_franchises values( '토스피아', '(주)푸드넷시스템', 90, 99982, 6703 );
insert into fastfood_franchises values( '도이첸', '(주)아라리', 36, 97309, 5574 );
insert into fastfood_franchises values( '코끼리가반한핫도그', '티에고(주)', 8, 70466, 10785 );
insert into fastfood_franchises values( '쉐이크포테이토', '(주)도남에프앤에프', 6, 66624, 5666 );
insert into fastfood_franchises values( '쏭쓰클럽핫도그', '(주)쏭앤킴푸드', 53, 55316, 58885 );
insert into fastfood_franchises values( '뉴욕핫도그앤커피', '(주)뉴욕핫도그앤커피', 120, 48858, 4696 );
insert into fastfood_franchises values( '비엔나핫도그', '(주)제이스타임', 6, 45320, 7661 );
insert into fastfood_franchises values( '팔팔핫도그', '(주)프로젝트비', 37, 14702, 1344 );

commit;

select * from fastfood_franchises order by franchises_num desc;