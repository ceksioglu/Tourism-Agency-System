PGDMP      $                |            tourism_agency    16.3    16.3 >    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16982    tourism_agency    DATABASE     �   CREATE DATABASE tourism_agency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Europe.utf8';
    DROP DATABASE tourism_agency;
                postgres    false            �            1259    17120    hotel    TABLE     U  CREATE TABLE public.hotel (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    city character varying(100) NOT NULL,
    region character varying(100) NOT NULL,
    address character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    phone character varying(20) NOT NULL,
    stars integer NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    17129    hotel_facility    TABLE     �   CREATE TABLE public.hotel_facility (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    facility character varying(50) NOT NULL
);
 "   DROP TABLE public.hotel_facility;
       public         heap    postgres    false            �            1259    17128    hotel_facility_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_facility_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.hotel_facility_id_seq;
       public          postgres    false    222            �           0    0    hotel_facility_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.hotel_facility_id_seq OWNED BY public.hotel_facility.id;
          public          postgres    false    221            �            1259    17119    hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.hotel_id_seq;
       public          postgres    false    220            �           0    0    hotel_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.hotel_id_seq OWNED BY public.hotel.id;
          public          postgres    false    219            �            1259    17141    hotel_pension_type    TABLE     �   CREATE TABLE public.hotel_pension_type (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    type character varying(50) NOT NULL
);
 &   DROP TABLE public.hotel_pension_type;
       public         heap    postgres    false            �            1259    17140    hotel_pension_type_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_pension_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.hotel_pension_type_id_seq;
       public          postgres    false    224            �           0    0    hotel_pension_type_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.hotel_pension_type_id_seq OWNED BY public.hotel_pension_type.id;
          public          postgres    false    223            �            1259    17192    reservation    TABLE     �  CREATE TABLE public.reservation (
    id integer NOT NULL,
    room_id integer NOT NULL,
    user_id integer NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    adult_count integer NOT NULL,
    child_count integer NOT NULL,
    total_price numeric(10,2) NOT NULL,
    guest_name character varying(255) NOT NULL,
    guest_surname character varying(255) NOT NULL,
    guest_identity_number character varying(20) NOT NULL,
    hotel_id integer NOT NULL,
    guest_phone character varying
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    17191    reservation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.reservation_id_seq;
       public          postgres    false    228            �           0    0    reservation_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;
          public          postgres    false    227            �            1259    17170    room    TABLE       CREATE TABLE public.room (
    id integer NOT NULL,
    hotel_id integer NOT NULL,
    season_id integer NOT NULL,
    pension_type_id integer NOT NULL,
    room_type character varying(20) NOT NULL,
    bed_count integer NOT NULL,
    size integer NOT NULL,
    tv boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    safe boolean NOT NULL,
    projector boolean NOT NULL,
    adult_price numeric(10,2) NOT NULL,
    child_price numeric(10,2) NOT NULL,
    stock integer NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    17169    room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.room_id_seq;
       public          postgres    false    226            �           0    0    room_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.room_id_seq OWNED BY public.room.id;
          public          postgres    false    225            �            1259    17051    season    TABLE     �   CREATE TABLE public.season (
    id integer NOT NULL,
    hotel_id integer,
    start_date date NOT NULL,
    end_date date NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    17050    season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.season_id_seq;
       public          postgres    false    218            �           0    0    season_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.season_id_seq OWNED BY public.season.id;
          public          postgres    false    217            �            1259    17033    user    TABLE     �   CREATE TABLE public."user" (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    role character varying(10) NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    17032    user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false    216                        0    0    user_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;
          public          postgres    false    215            :           2604    17281    hotel id    DEFAULT     d   ALTER TABLE ONLY public.hotel ALTER COLUMN id SET DEFAULT nextval('public.hotel_id_seq'::regclass);
 7   ALTER TABLE public.hotel ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            ;           2604    17282    hotel_facility id    DEFAULT     v   ALTER TABLE ONLY public.hotel_facility ALTER COLUMN id SET DEFAULT nextval('public.hotel_facility_id_seq'::regclass);
 @   ALTER TABLE public.hotel_facility ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222            <           2604    17283    hotel_pension_type id    DEFAULT     ~   ALTER TABLE ONLY public.hotel_pension_type ALTER COLUMN id SET DEFAULT nextval('public.hotel_pension_type_id_seq'::regclass);
 D   ALTER TABLE public.hotel_pension_type ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223    224            >           2604    17284    reservation id    DEFAULT     p   ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);
 =   ALTER TABLE public.reservation ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227    228            =           2604    17285    room id    DEFAULT     b   ALTER TABLE ONLY public.room ALTER COLUMN id SET DEFAULT nextval('public.room_id_seq'::regclass);
 6   ALTER TABLE public.room ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    226    226            9           2604    17286 	   season id    DEFAULT     f   ALTER TABLE ONLY public.season ALTER COLUMN id SET DEFAULT nextval('public.season_id_seq'::regclass);
 8   ALTER TABLE public.season ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            8           2604    17287    user id    DEFAULT     d   ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);
 8   ALTER TABLE public."user" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            �          0    17120    hotel 
   TABLE DATA           U   COPY public.hotel (id, name, city, region, address, email, phone, stars) FROM stdin;
    public          postgres    false    220   J       �          0    17129    hotel_facility 
   TABLE DATA           @   COPY public.hotel_facility (id, hotel_id, facility) FROM stdin;
    public          postgres    false    222   �W       �          0    17141    hotel_pension_type 
   TABLE DATA           @   COPY public.hotel_pension_type (id, hotel_id, type) FROM stdin;
    public          postgres    false    224   JZ       �          0    17192    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, user_id, start_date, end_date, adult_count, child_count, total_price, guest_name, guest_surname, guest_identity_number, hotel_id, guest_phone) FROM stdin;
    public          postgres    false    228   �\       �          0    17170    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, season_id, pension_type_id, room_type, bed_count, size, tv, minibar, game_console, safe, projector, adult_price, child_price, stock) FROM stdin;
    public          postgres    false    226   ^       �          0    17051    season 
   TABLE DATA           D   COPY public.season (id, hotel_id, start_date, end_date) FROM stdin;
    public          postgres    false    218   >`       �          0    17033    user 
   TABLE DATA           >   COPY public."user" (id, username, password, role) FROM stdin;
    public          postgres    false    216   b                  0    0    hotel_facility_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.hotel_facility_id_seq', 161, true);
          public          postgres    false    221                       0    0    hotel_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.hotel_id_seq', 67, true);
          public          postgres    false    219                       0    0    hotel_pension_type_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.hotel_pension_type_id_seq', 148, true);
          public          postgres    false    223                       0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 54, true);
          public          postgres    false    227                       0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 73, true);
          public          postgres    false    225                       0    0    season_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.season_id_seq', 132, true);
          public          postgres    false    217                       0    0    user_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.user_id_seq', 12, true);
          public          postgres    false    215            H           2606    17134 "   hotel_facility hotel_facility_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.hotel_facility
    ADD CONSTRAINT hotel_facility_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.hotel_facility DROP CONSTRAINT hotel_facility_pkey;
       public            postgres    false    222            J           2606    17146 *   hotel_pension_type hotel_pension_type_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.hotel_pension_type
    ADD CONSTRAINT hotel_pension_type_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.hotel_pension_type DROP CONSTRAINT hotel_pension_type_pkey;
       public            postgres    false    224            F           2606    17127    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    220            N           2606    17199    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    228            L           2606    17175    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    226            D           2606    17056    season season_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    218            @           2606    17038    user user_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    216            B           2606    17040    user user_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_username_key;
       public            postgres    false    216            O           2606    17271 +   hotel_facility hotel_facility_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_facility
    ADD CONSTRAINT hotel_facility_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;
 U   ALTER TABLE ONLY public.hotel_facility DROP CONSTRAINT hotel_facility_hotel_id_fkey;
       public          postgres    false    222    4678    220            P           2606    17276 3   hotel_pension_type hotel_pension_type_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_pension_type
    ADD CONSTRAINT hotel_pension_type_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id) ON DELETE CASCADE;
 ]   ALTER TABLE ONLY public.hotel_pension_type DROP CONSTRAINT hotel_pension_type_hotel_id_fkey;
       public          postgres    false    4678    224    220            T           2606    17210 %   reservation reservation_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id);
 O   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_hotel_id_fkey;
       public          postgres    false    220    228    4678            U           2606    17258 $   reservation reservation_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id);
 N   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_room_id_fkey;
       public          postgres    false    228    4684    226            V           2606    17263 $   reservation reservation_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(id);
 N   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_user_id_fkey;
       public          postgres    false    228    4672    216            Q           2606    17243    room room_hotel_id_fkey    FK CONSTRAINT     w   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(id);
 A   ALTER TABLE ONLY public.room DROP CONSTRAINT room_hotel_id_fkey;
       public          postgres    false    220    226    4678            R           2606    17186    room room_pension_type_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pension_type_id_fkey FOREIGN KEY (pension_type_id) REFERENCES public.hotel_pension_type(id);
 H   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pension_type_id_fkey;
       public          postgres    false    224    226    4682            S           2606    17238    room room_season_id_fkey    FK CONSTRAINT     z   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_season_id_fkey FOREIGN KEY (season_id) REFERENCES public.season(id);
 B   ALTER TABLE ONLY public.room DROP CONSTRAINT room_season_id_fkey;
       public          postgres    false    4676    218    226            �   {  x��X�v�8]#_�}�>�܍��q�Gܖ;>�3H�$X���n�#�;��`�����z$�NGd�
U��u�=����[p&��35�U���i���+KC~�ꮑ��͚OF|��W.�P�L��2j������l�/"�D�a����w�4����F�s�O��e�ظ[��ţ�3��"[])���4Kmz>q7	
S�%N��ʙz1'ˆ��Pqah���A>���&Tr��M��J��[#��c�gSU��B�lF��Tr*G���<!�����O��l;�K�Y���F�a���D��-�6�>>��t�IG6����Ϣ{�����#Nt�Q�E�I����
ڲ���F�d'�K~�揎�0��e�\u�~>���gZ}�Z/��"��(��z2v�7O|\�q#��}Ot����ҍ\�I7�6��ӽ_r$��2sva��O�lN���l�ڲ��#e�"@Y�6N�l�r�N��Y��]�d�~PF)b�]���-�1u�8��w93�N��{|mumF�, ��Q�eEB��DlfNW��AYa����'}�j]�}%كYo��A��o亟���l��f.���0�&Q>Q�fN�� ��� �r�xQP'B6Y�z���Z��R>�F�N?����x�ND��f�(-�Ի�����,�� Β��	k�5&��a�3�4��5����G�L���e����xͳ0>a*�v�F���Y��3+BT#�%�q�����O�!/�S��)E�zn���ܶ$goWVAQdV_��;��j��7Ů�6싖���:�h��7��U��O�^X�8��H���o����4�#��(I���&�p��y��٩��ϧ�_�- o������r�O���F���"�3�Nc�O���E䱝-)s���8�����A@����fs��ჸ�#!�4r�e�QVs�,��%�Iv�^TSm�������YY��<�]i��i���<����yΝ�W'q��h�>~ �B]�[��1Nj���{�(�8����;Q��.��D�������ŢR�_�p��]^!�;�4:	����T�G
<fT�M��<+x��t������L+��a�����jn�.�n6�%׀���������_{5U3{���3s�
a.�����(����۟���3�A��������'��.,l�E1P��Z5-��y�]\C��������~p�/�y'E��r�E�R�y�Rۈ�"DX����1������^
;;��gY#�|{�|r�?h�"�Z��
�� ��/�������2'{��EiQ�V���G�v(���T�jӰ�
qP�o<��'4����u4�ŏ4Q�/����}�[ij���;��Q.R��%�덡�<���w�b���,��08ñR)�M�����ajf3�0M�2�ݑ��iD��:�l\!a �U%����7S���єn��l���G@8�j%j�Lv��yN'I����W�+�![���Ie�賕�k>l6������F������ dٶ�'#��2��Gƾ��*��n3�(�	��(E�'.�r�}�U��:K/Zd��ډ+��w̆�Pqүw�.)Q$��j)���/��
Nc��0�'������Nv�T���r Fc�T�j�(�J.U�5ؤ���K,����x��cxT<�W��;�%/��<�;i�uD��
I�!�m |�>4j���������n�[[|I�RX =�Pd����A�j��gIÓ())MbO_P�Q�o��l�>���Y�����<��C�A�y!v��V��5VPT����n���#�j>4Jկu�ՍChq<�C�����0њx�%�d�ƶ��/`�@��Cu�R r؝|�s�N�j������O�{�$'u���	ǋ�@�R�ވ	��K�������U�8a�U��R:���ߕ
�*�����������0�<A�L�Y�H�e�^�(HQ�N1Q�~Ƿ-^�ٽ��}��%j��S��U�j�(���_���$�XT˟3p` ���|��q��h�����BZ�;��\�Q�p��	��#���z��h�Ok�HR��������)Ļ��U�+5�+�	�Z�U�k7T4��e�7�v"u�诖C�b�!��8%HM�g�b�8�������c�<�=�j�l�5�$����WZ��~ֈ��XĻ��1�C��/�������0�6��4*Z&����U0�͐�`��)�4�s��Ϥ=	���8e�6i�6�P��a�;�q�Q%kPp��� ~���ٹhu}	N�D ���S�_�o%�M��>|��8�@��Y�.�#r�A�Zo�wt;�f�n�z�'#̀ ��CG�"'"�h�I�l/�]�y���@���訓�Q��x��3����L߭������N�����g���G��Q@Q?PdeF� u���9܁��<#�� (�������]�#�m����V�zX�3��.ܚ	kY��=�5y�VK�Eᮯ\��q�C��seW>�p�i��eRL�ӡnY`�(�W�7F�=1��
�E~%��u�$P�q�<~'k	ji�G�( w/soИ����(!r	=V�/�m�~Е�]����Y��ނhn��֠t�ߌ����o�"�#��WK�.�M?��Fk���@˟!"���$7N��w}c�i"v��6� �t ����?v����ގ[֌Z�(><��U")�mAk�r�Iz�7b��PM�m�Y�[i��j����h�Feg��7jqV�iMC �� �ꏮ�@�����zO�kGk�_6��9}=�^�"��ȭ�>=ZL�`�$�Y�	���U�yѝ�s��Ȍ�g�I6�<�>�n������S�d�%Gi���Or<�O*]7'�'�؁�b7��rcV����[?=����Ӗ���D.m�S^�����C�r|����pМ�;[E���H��V$��b R��`�o]E�o�[5��}�#c̼���OMK�x3f6����9n
�<�vG�!xj��I��*���]��3&���ޚ���%A��%U 3��e�N+c6-Z����8��'�k's�D��:�����B>d�OI�(���(��~�h������|��&IяH�&S#K���<� �"�K��GUl�|�Dis�QY��1�nc�b3�-��|�����1�'����To �{��&X�q�_�CNX��Cx�1�=U�	���Y���/`������EO##�A5�u��,�.�R���e[v|��W(Y`[k���?H��68��w��k$l��_[��� �N�0�F�_������)^��ˎ�|���E�����!Z@�2�/�;`^�>�c��z�"{!;�b4r6?AǛ�����7��$�i��$��,wK��֦뗎C��>oX���Ƭ����-E����ݻw���w`      �   �  x�m�I��@E��a�Y�eÐ;B�l#�	���"�R<��]�k��*��i������k9~N����2eJt�^��~>����_n��z����m�L�2�<����t�-��s��z����N��u��^v�$ ���T�>k���+c�Θ���Xp0�XI��du� �턄^��R5�H�k�))�5΄x�Sѿ�=U��8}b����xՋ���]�sx��%#4;}Nʜ>#w��(U}Ff��̊�\��]Q���PT�/A�חH���Kғ�\�du�<њ�T��p���y}#��A/JW=2W��A��U��m��Y}E�6tYQ���e�W֓�#�+v�'�u�u�zʜ�#�m����3��=E/cPd�Y��7�zQ8葾ns&����b��Dk�����[Q���e�7V�ꑹ;�(zsu�6q$(�z�����z�dW�=�9[��b?њH0��׋2�G�:�{P����l�=)z���l�^Y}Gj���
=��M/z�z,��7Kx�m�HhH7�CR��~ȄFN(�ו*�m��	���بK���_�����^��k
����D�ƨ��[���dX~��!��'�(�Rt��)0��T���^��<��=����l*��f?H"���1Ѳ<.шf����!��}@���
����="<�+���4M�6|      �   �  x�u��n1Eל�)�����bt��@�����eQx8i��97�����9?��a�_��q~~]�� �%�ݗ����~>���S�<�S��~�2E�<̗�G���|z-��{*������e~�����|�>��RO�Q6
��8+��>�4��jW�3
j;�
�MAX�'��<A~ �������%a�'dBx%X�*��*T�8B���N*�![�*��(E�$�-����"K�+�X�
UaV�z������'��-��B	/$t�R��a��I��X�*��j�*`B�<�N�=��Xl�rf�22��O�PF��
e��HiZ_�JŰF�ZF��@%Ğp���	�B	�n �I�=!�0�F�Y�*�[��� -�YMB'ǁPA�
� Ð�X��*��(U�
!A
U)v��lYuP��g��j��j$���PMrm�Lxh=��R1{'턪@-�(Y5'�-Ԑ�GB-H���������LU5����)�*Ǒ��/2�d�'���\�י���W�KB���2a`�֫c���
a�XX.o��`�=�͉9�za7�a�<v"S��0�z,;���g�x!T�zU!ʫ�����}�b�}�bx�G�t���c��f�x<H�}n�����˻��qWy������/k��k      �   �   x�m��j�0E�W��)[��Uɪt�M(��IZ��w$j�@�;B�qtgd=
!��8�M����������ކ��D�*N���(	Ej�f3?��2s��|]�3^�R�P"qY:w�N�%Y��	�N=�&�������,�*M�lu�)9�}>�L�ZNY��5���V��2e�:$W����aNWs�;N��m�����svFn֜sDrA/�kQ���\N8|�r�)`�P�=��Y$}�SJ�;�]"      �   &  x��V�jA<�?ƴ��k�	�v��-_��'%���b��`Yը�R��TT�T��]�<=
�e���o|4燜�jNfA0������r�G���g�"j���|yy��v��rV�eY>ْՠU�r��6�<�E����qd��A�8W��,{u̠M�d���3� $�(�pO�bA3V��ѫB�Q�� ٨=� �F� L�Q���k~H6������c�������5h�գ6��@V�ڃ�Y=�p; n�1�6i=����o������pO�fA3V�Vܬ��-�H�Ѻ��llak�o�¤��gǭ�����vu�����zc�7��'�'M�(�CM��W��5�	K, U�J��^��C�%�5���]!Ԁ�����J��Y���4���x;�h�R����BZ�J�s���x��u����$g���G*�)�E��)�8~Kz��r� y�1j��u�U0k�g����J�?m3K��}����#K�?e�;�H����y���e���9��ϩ�{��z�o���=��CJ�?V�b      �   1  x�u�[��@C����Q��ҽ���:�&��k��É�q�{�m�������?���M�d�Ɍa�u���1�S7y:W,��<�i��n��z�rָ�X3.g�����U7��}#�N�W���Ȟ���m-�x3���Xd�etW�Qd�]�]E�&����F&�G.�(s���\\U�"����"����M&��[��=��#ח5��Å�%�L<\ חUd�!hח���C.�/���OG�/9���p}�E&���K�x8��Kn2�NLחjdO�zLחd�͘�/������>~�L�+��Km2�����䁇\\_�A&rq}��x����*2��˵��;�\_v#{z��r}كL���e/2�2���.2�X�d�d�!ח��d� ח3��C.�/g���\\_N���\\_�&k����F(����� TsF������HךފP�+����M�&����x��EBv�b�����ci�/6W��a�v�b=����Hi0vP�	��Q�	Xe��9P�\��� ��fo��~	�|����i��i��g�������pZ{�`���W��y�^� �9dm      �   �   x�M�?�@��9�a���;�]]
�A�k���Z���=Y^�a�}
�<ԺMˈp�����>h�1��R�R�<���RwU�[C�	�X^���D�@�e�`�"r�F������أ�J���}RJ��r�     