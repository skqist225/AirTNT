package com.airtnt.backend.amentity;

import com.airtnt.common.entity.Amentity;
import com.airtnt.common.entity.AmentityCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

@SpringBootTest
class AmentityRepositoryTest {

        @Autowired
        private AmentityRepository repository;

        @Autowired
        private AmentityCategoryRepository amentityCategoryRepository;

        @Test
        void testAddAmentityWithExistingAmtCategory() {
                AmentityCategory amentityCategory = new AmentityCategory(3);
                Amentity amentity = Amentity.builder().name("Tiện nghi thiết yếu test").iconImage(
                                "<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M11 1v7l1.898 20.819.007.174c-.025 1.069-.804 1.907-1.818 1.999a2 2 0 0 1-.181.008h-7.81l-.174-.008C1.86 30.87 1.096 30.018 1.096 29l.002-.09 1.907-21L3.001 1zm6 0l.15.005a2 2 0 0 1 1.844 1.838L19 3v7.123l-2 8V31h-2V18.123l.007-.163.02-.162.033-.16L16.719 11H13V1zm11 0a2 2 0 0 1 1.995 1.85L30 3v26a2 2 0 0 1-1.85 1.995L28 31h-7v-2h7v-2h-7v-2h7v-2h-7v-2h7v-2h-7v-2h7v-2h-7v-2h7v-2h-7V9h7V7h-7V5h7V3h-7V1zM9.088 9h-4.18L3.096 29l.058.002L10.906 29l-.004-.058zM17 3h-2v6h2zM9.002 3H5L5 7h4.004z\"></path></svg>")
                                .description("Khăn tắm, khăn trải giường, xà phòng và giấy vệ sinh")
                                .amentityCategory(amentityCategory).build();

                repository.save(amentity);
                assertThat(amentity.getId()).isPositive();
        }

        @Test
        void testAddListAmentityWithExistingAmtCategory() {
                AmentityCategory bathRoomAmentity = new AmentityCategory(1);
                Amentity amentity2 = Amentity.builder().name("Máy sấy tóc").iconImage(
                                "<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M14 27l-.005.2a4 4 0 0 1-3.789 3.795L10 31H4v-2h6l.15-.005a2 2 0 0 0 1.844-1.838L12 27zM10 1c.536 0 1.067.047 1.58.138l.38.077 17.448 3.64a2 2 0 0 1 1.585 1.792l.007.166v6.374a2 2 0 0 1-1.431 1.917l-.16.04-13.554 2.826 1.767 6.506a2 2 0 0 1-1.753 2.516l-.177.008H11.76a2 2 0 0 1-1.879-1.315l-.048-.15-1.88-6.769A9 9 0 0 1 10 1zm5.692 24l-1.799-6.621-1.806.378a8.998 8.998 0 0 1-1.663.233l-.331.008L11.76 25zM10 3a7 7 0 1 0 1.32 13.875l.331-.07L29 13.187V6.813L11.538 3.169A7.027 7.027 0 0 0 10 3zm0 2a5 5 0 1 1 0 10 5 5 0 0 1 0-10zm0 2a3 3 0 1 0 0 6 3 3 0 0 0 0-6z\"></path></svg>")
                                .amentityCategory(bathRoomAmentity).build();
                Amentity amentity3 = Amentity.builder().name("Dầu gội đầu").iconImage(
                                "<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M20 1v2h-3v2h1a2 2 0 0 1 1.995 1.85L20 7v2a4 4 0 0 1 3.995 3.8L24 13v14a4 4 0 0 1-3.8 3.995L20 31h-8a4 4 0 0 1-3.995-3.8L8 27V13a4 4 0 0 1 3.8-3.995L12 9V7a2 2 0 0 1 1.85-1.995L14 5h1V3H8V1zm2 21H10v5a2 2 0 0 0 1.85 1.995L12 29h8a2 2 0 0 0 1.995-1.85L22 27zm0-6H10v4h12zm-2-5h-8a2 2 0 0 0-1.995 1.85L10 13v1h12v-1a2 2 0 0 0-2-2zm-2-4h-4v2h4z\"></path></svg>")
                                .amentityCategory(bathRoomAmentity).build();

                AmentityCategory routineAmentity = new AmentityCategory(4);
                Amentity amentity1 = Amentity.builder().name("Tiện nghi thiết yếu").iconImage(
                                "<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M11 1v7l1.898 20.819.007.174c-.025 1.069-.804 1.907-1.818 1.999a2 2 0 0 1-.181.008h-7.81l-.174-.008C1.86 30.87 1.096 30.018 1.096 29l.002-.09 1.907-21L3.001 1zm6 0l.15.005a2 2 0 0 1 1.844 1.838L19 3v7.123l-2 8V31h-2V18.123l.007-.163.02-.162.033-.16L16.719 11H13V1zm11 0a2 2 0 0 1 1.995 1.85L30 3v26a2 2 0 0 1-1.85 1.995L28 31h-7v-2h7v-2h-7v-2h7v-2h-7v-2h7v-2h-7v-2h7v-2h-7v-2h7v-2h-7V9h7V7h-7V5h7V3h-7V1zM9.088 9h-4.18L3.096 29l.058.002L10.906 29l-.004-.058zM17 3h-2v6h2zM9.002 3H5L5 7h4.004z\"></path></svg>")
                                .amentityCategory(routineAmentity).build();

                AmentityCategory entertainmentAmentity = new AmentityCategory(5);
                Amentity amentity4 = Amentity.builder().name("TV").iconImage(
                                "<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M9 29v-2h2v-2H6a5 5 0 0 1-4.995-4.783L1 20V8a5 5 0 0 1 4.783-4.995L6 3h20a5 5 0 0 1 4.995 4.783L31 8v12a5 5 0 0 1-4.783 4.995L26 25h-5v2h2v2zm10-4h-6v2h6zm7-20H6a3 3 0 0 0-2.995 2.824L3 8v12a3 3 0 0 0 2.824 2.995L6 23h20a3 3 0 0 0 2.995-2.824L29 20V8a3 3 0 0 0-2.824-2.995z\"></path></svg>")
                                .amentityCategory(entertainmentAmentity).build();

                AmentityCategory coolSysAmentity = new AmentityCategory(6);
                Amentity amentity5 = Amentity.builder().name("Điều hòa nhiệt độ").iconImage(
                                "<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M17 1v4.03l4.026-2.324 1 1.732L17 7.339v6.928l6-3.464V5h2v4.648l3.49-2.014 1 1.732L26 11.381l4.026 2.325-1 1.732L24 12.535l-6 3.464 6 3.465 5.026-2.902 1 1.732L26 20.618l3.49 2.016-1 1.732L25 22.351V27h-2v-5.804l-6-3.465v6.929l5.026 2.902-1 1.732L17 26.97V31h-2v-4.031l-4.026 2.325-1-1.732L15 24.66v-6.929l-6 3.464V27H7v-4.65l-3.49 2.016-1-1.732 3.489-2.016-4.025-2.324 1-1.732 5.025 2.901 6-3.464-6-3.464-5.025 2.903-1-1.732L6 11.38 2.51 9.366l1-1.732L7 9.648V5h2v5.803l6 3.464V7.339L9.974 4.438l1-1.732L15 5.03V1z\"></path></svg>")
                                .amentityCategory(coolSysAmentity).build();
                Amentity amentity6 = Amentity.builder().name("Hệ thống sưởi").iconImage(
                                "<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M16 0a5 5 0 0 1 4.995 4.783L21 5l.001 12.756.26.217a7.984 7.984 0 0 1 2.717 5.43l.017.304L24 24a8 8 0 1 1-13.251-6.036l.25-.209L11 5A5 5 0 0 1 15.563.019l.22-.014zm0 2a3 3 0 0 0-2.995 2.824L13 5v13.777l-.428.298a6 6 0 1 0 7.062.15l-.205-.15-.428-.298L19 11h-4V9h4V7h-4V5h4a3 3 0 0 0-3-3zm1 11v7.126A4.002 4.002 0 0 1 16 28a4 4 0 0 1-1-7.874V13zm-1 9a2 2 0 1 0 0 4 2 2 0 0 0 0-4z\"></path></svg>")
                                .amentityCategory(coolSysAmentity).build();

                Iterator<Amentity> amentities = repository
                                .saveAll(List.of(amentity1, amentity2, amentity3, amentity4, amentity5, amentity6))
                                .iterator();

                // Convert iterator to list
                List<Amentity> result = new ArrayList<>();
                amentities.forEachRemaining(result::add);

                assertThat(result.size()).isEqualTo(6);
        }

        @Test
        void testAddAmentityInNotIncludedCategory() {
                AmentityCategory notIncludedCategory = new AmentityCategory(7);
                AmentityCategory wifiCat = new AmentityCategory(5);
                List<String> svgList = new ArrayList<>();
                svgList.add("<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M2 6.242l2 2V16h3.1a4.998 4.998 0 0 1 1.763-2.894l1.428 1.428a3 3 0 1 0 4.175 4.175l1.428 1.428a4.997 4.997 0 0 1-2.978 1.78 5.002 5.002 0 0 1-4.7 4.078L8 26H4v4H2V20h2v4h4a3.001 3.001 0 0 0 2.872-2.13A5.004 5.004 0 0 1 7.1 18.002L4 18a2 2 0 0 1-1.995-1.85L2 16V6.242zm1.707-3.95l26 26-1.414 1.415-26-26 1.414-1.414zM7.242 3H23a2 2 0 0 1 1.994 1.85L25 5v1.522l5-1.999v11.954l-5-2V16a2 2 0 0 1-1.85 1.994L23 18h-.757l-2-2H23V5H9.242l-2-2zM28 7.476l-3 1.2v3.647l3 1.2V7.476z\"></path></svg>");
                svgList.add("<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M24.5 28.743l2 2V31h-2v-2.257zm-10-10l5.995 5.995.005.262c0 3.27-2.183 6-5 6s-5-2.73-5-6c0-2.872 1.683-5.326 4-5.882v-.375zM1.5 5.742l2 2V19h3.995l-.017-.964-.027-.949c-.078-2.232-.249-4.195-.498-5.892l2.276 2.276c.167 1.819.258 3.831.27 6.034L9.5 21H3.499L3.5 31h-2V5.742zm2.207-3.45l26 26-1.414 1.415-26-26 1.414-1.414zM15.5 21c-1.602 0-3 1.748-3 4s1.398 4 3 4 3-1.748 3-4-1.398-4-3-4zm10-20a5 5 0 0 1 5 5l-.002.369-.014.74a40.634 40.634 0 0 1-.27 3.67l-.093.715c-.033.237-.068.471-.104.704l-.117.69-.128.677-.14.66c-.7 3.142-1.789 5.628-3.131 6.468l-.001 1.564-3.685-3.684c-1.403-2.792-2.272-7.59-2.314-12.228L20.5 6l.005-.217A5 5 0 0 1 25.5 1zm-1 2.17a3.002 3.002 0 0 0-1.995 2.654L22.5 6l.002.31c.045 4.321 1.031 9.133 1.999 11.39V3.17zm2 .001v14.526c.99-2.31 2-7.298 2-11.697a3.001 3.001 0 0 0-2-2.829zM16.5 1v11.258l-2-2V1h2z\"></path></svg>");
                svgList.add("<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M24.5 28.743l2 2V31h-2v-2.257zm-10-10l5.995 5.995.005.262c0 3.27-2.183 6-5 6s-5-2.73-5-6c0-2.872 1.683-5.326 4-5.882v-.375zM1.5 5.742l2 2V19h3.995l-.017-.964-.027-.949c-.078-2.232-.249-4.195-.498-5.892l2.276 2.276c.167 1.819.258 3.831.27 6.034L9.5 21H3.499L3.5 31h-2V5.742zm2.207-3.45l26 26-1.414 1.415-26-26 1.414-1.414zM15.5 21c-1.602 0-3 1.748-3 4s1.398 4 3 4 3-1.748 3-4-1.398-4-3-4zm10-20a5 5 0 0 1 5 5l-.002.369-.014.74a40.634 40.634 0 0 1-.27 3.67l-.093.715c-.033.237-.068.471-.104.704l-.117.69-.128.677-.14.66c-.7 3.142-1.789 5.628-3.131 6.468l-.001 1.564-3.685-3.684c-1.403-2.792-2.272-7.59-2.314-12.228L20.5 6l.005-.217A5 5 0 0 1 25.5 1zm-1 2.17a3.002 3.002 0 0 0-1.995 2.654L22.5 6l.002.31c.045 4.321 1.031 9.133 1.999 11.39V3.17zm2 .001v14.526c.99-2.31 2-7.298 2-11.697a3.001 3.001 0 0 0-2-2.829zM16.5 1v11.258l-2-2V1h2z\"></path></svg>");
                svgList.add("<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M2 6.242l2 2V28h19.757l2 2H4a2 2 0 0 1-1.995-1.85L2 28V6.242zm1.707-3.95l26 26-1.414 1.415-26-26 1.414-1.414zM28 2a2 2 0 0 1 1.994 1.85L30 4v21.757l-2-2V4H8.242L6.236 2.005 28 2zM7.877 12.12l2.383 2.38h-.101c-.342 0-.68.024-1.014.073a7 7 0 0 0 9.207 8.022l1.527 1.528A9 9 0 0 1 7.877 12.12zM16 7a9 9 0 0 1 8.123 12.88l-2.695-2.694h.04c.493 0 .98-.05 1.456-.151a7 7 0 0 0-9.277-7.63L12.12 7.877A8.965 8.965 0 0 1 16 7z\"></path></svg>");
                svgList.add("<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M3.486 7.727l1.447 1.448A12.94 12.94 0 0 0 3 16c0 7.18 5.82 13 13 13 2.503 0 4.84-.707 6.824-1.933l1.448 1.448A14.93 14.93 0 0 1 16 31C7.716 31 1 24.284 1 16c0-3.057.915-5.901 2.486-8.273zm.221-5.434l26 26-1.414 1.414-26-26 1.414-1.414zM16 1c8.284 0 15 6.716 15 15 0 3.057-.914 5.9-2.485 8.272l-1.448-1.448A12.94 12.94 0 0 0 29 16c0-7.18-5.82-13-13-13a12.94 12.94 0 0 0-6.825 1.933L7.727 3.486A14.93 14.93 0 0 1 16 1zm-4.9 16a5.006 5.006 0 0 0 3.9 3.9v2.03A7.005 7.005 0 0 1 9.071 17h2.03zm5.9 4.243l1.352 1.352a6.954 6.954 0 0 1-1.351.334L17 21.243zM21.243 17l1.686.001c-.067.467-.18.919-.334 1.351L21.243 17zm-4.242-7.929A7.005 7.005 0 0 1 22.929 15H20.9A5.006 5.006 0 0 0 17 11.1l.001-2.029zm-7.596 4.577L10.757 15 9.071 15c.067-.467.18-.92.334-1.352zM15 9.071l-.001 1.686-1.35-1.352A6.954 6.954 0 0 1 15 9.07zM23 8a1 1 0 1 1 0 2 1 1 0 0 1 0-2z\"></path></svg>");
                svgList.add("<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M5 9.242l2 2L6.999 29h17.758l2 2H3v-2h1.999L5 9.242zm-1.293-6.95l26 26-1.414 1.415-26-26 1.414-1.414zM25 1a2 2 0 0 1 1.994 1.85L27 3l-.001 19.756-2-1.999L25 3H7.242l-1.53-1.53a1.991 1.991 0 0 1 1.139-.465L7 1h18zm-3 14a1 1 0 1 1 0 2 1 1 0 0 1 0-2z\"></path></svg>");
                svgList.add("<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"m11.6667 0-.00095 1.666h8.667l.00055-1.666h2l-.00055 1.666 6.00065.00063c1.0543745 0 1.9181663.81587127 1.9945143 1.85073677l.0054857.14926323v15.91907c0 .4715696-.1664445.9258658-.4669028 1.2844692l-.1188904.1298308-8.7476886 8.7476953c-.3334303.3332526-.7723097.5367561-1.2381975.5778649l-.1758207.0077398h-12.91915c-2.68874373 0-4.88181754-2.1223321-4.99538046-4.7831124l-.00461954-.2168876v-21.66668c0-1.05436021.81587582-1.91815587 1.85073739-1.99450431l.14926261-.00548569 5.999-.00063.00095-1.666zm16.66605 11.666h-24.666v13.6673c0 1.5976581 1.24893332 2.9036593 2.82372864 2.9949072l.17627136.0050928 10.999-.0003.00095-5.6664c0-2.6887355 2.122362-4.8818171 4.7832071-4.9953804l.2168929-.0046196 5.66595-.0006zm-.081 8-5.58495.0006c-1.5977285 0-2.9037573 1.2489454-2.9950071 2.8237299l-.0050929.1762701-.00095 5.5864zm-18.586-16-5.999.00062v5.99938h24.666l.00065-5.99938-6.00065-.00062.00055 1.66733h-2l-.00055-1.66733h-8.667l.00095 1.66733h-2z\"></path></svg>");
                svgList.add("<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"m15.9999 20.33323c2.0250459 0 3.66667 1.6416241 3.66667 3.66667s-1.6416241 3.66667-3.66667 3.66667-3.66667-1.6416241-3.66667-3.66667 1.6416241-3.66667 3.66667-3.66667zm0 2c-.9204764 0-1.66667.7461936-1.66667 1.66667s.7461936 1.66667 1.66667 1.66667 1.66667-.7461936 1.66667-1.66667-.7461936-1.66667-1.66667-1.66667zm.0001-7.33323c3.5168171 0 6.5625093 2.0171251 8.0432368 4.9575354l-1.5143264 1.5127043c-1.0142061-2.615688-3.5549814-4.4702397-6.5289104-4.4702397s-5.5147043 1.8545517-6.52891042 4.4702397l-1.51382132-1.5137072c1.48091492-2.939866 4.52631444-4.9565325 8.04273174-4.9565325zm.0001-5.3332c4.9804693 0 9.3676401 2.540213 11.9365919 6.3957185l-1.4470949 1.4473863c-2.1746764-3.5072732-6.0593053-5.8431048-10.489497-5.8431048s-8.31482064 2.3358316-10.48949703 5.8431048l-1.44709488-1.4473863c2.56895177-3.8555055 6.95612261-6.3957185 11.93659191-6.3957185zm-.0002-5.3336c6.4510616 0 12.1766693 3.10603731 15.7629187 7.9042075l-1.4304978 1.4309874c-3.2086497-4.44342277-8.4328305-7.3351949-14.3324209-7.3351949-5.8991465 0-11.12298511 2.89133703-14.33169668 7.334192l-1.43047422-1.4309849c3.58629751-4.79760153 9.31155768-7.9032071 15.7621709-7.9032071z\"></path></svg>");

                List<String> amentityName = new ArrayList<>();
                amentityName.add("Camera an ninh trong nhà");
                amentityName.add("Bếp");
                amentityName.add("Máy giặt");
                amentityName.add("Máy báo khói");
                amentityName.add("Máy phát hiện khói CO");
                amentityName.add("Lối vào riêng");
                amentityName.add("Cho phép ở dài hạn");
                amentityName.add("Wifi");

                if (svgList.size() == amentityName.size()) {
                        for (int i = 0; i < svgList.size(); i++) {
                                Amentity amentity;
                                if (i >= amentityName.size() - 2) {
                                        amentity = Amentity.builder().name(amentityName.get(i))
                                                        .iconImage(svgList.get(i)).amentityCategory(wifiCat).build();
                                } else {
                                        amentity = Amentity.builder().name(amentityName.get(i))
                                                        .iconImage(svgList.get(i)).amentityCategory(notIncludedCategory)
                                                        .build();
                                }

                                repository.save(amentity);
                        }
                }
        }

        @Test
        void updateAmentityDescription() {
                String mbkDesc = "Chỗ ở này có thể không có máy báo khói. Hãy liên hệ với chủ nhà nếu bạn có bất kỳ câu hỏi nào.";
                String coDesc = "Chỗ ở này có thể không có máy phát hiện khí CO. Hãy liên hệ với chủ nhà nếu bạn có bất kỳ câu hỏi nào.";
                String tntyDesc = "Khăn tắm, khăn trải giường, xà phòng và giấy vệ sinh";
                String cpodhDesc = "Cho phép ở từ 28 ngày trở lên";
                String wifiDesc = "Có sẵn trong toàn bộ nhà/phòng cho thuê";

                Amentity mbk = repository.findById(10).get();
                Amentity co = repository.findById(11).get();
                Amentity amentity3 = repository.findById(1).get();
                Amentity cpodh = repository.findById(13).get();
                Amentity wifi = repository.findById(14).get();

                mbk.setDescription(mbkDesc);
                co.setDescription(coDesc);
                amentity3.setDescription(tntyDesc);
                cpodh.setDescription(cpodhDesc);
                wifi.setDescription(wifiDesc);

                repository.save(mbk);
                repository.save(co);
                repository.save(amentity3);
                repository.save(cpodh);
                repository.save(wifi);

        }

        @Test
        @Transactional
        void testAddAmentityWithNonExistingAmentityCategory() {
                AmentityCategory amentityCategory = AmentityCategory.builder().name("Cần thiết tesbbvdsfat!!!").build();

                AmentityCategory saved = amentityCategoryRepository.save(amentityCategory);

                Amentity amentity = Amentity.builder().name("Tiện nghi cần test").iconImage(
                                "<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 24px; width: 24px; fill: currentcolor;\"><path d=\"M11 1v7l1.898 20.819.007.174c-.025 1.069-.804 1.907-1.818 1.999a2 2 0 0 1-.181.008h-7.81l-.174-.008C1.86 30.87 1.096 30.018 1.096 29l.002-.09 1.907-21L3.001 1zm6 0l.15.005a2 2 0 0 1 1.844 1.838L19 3v7.123l-2 8V31h-2V18.123l.007-.163.02-.162.033-.16L16.719 11H13V1zm11 0a2 2 0 0 1 1.995 1.85L30 3v26a2 2 0 0 1-1.85 1.995L28 31h-7v-2h7v-2h-7v-2h7v-2h-7v-2h7v-2h-7v-2h7v-2h-7v-2h7v-2h-7V9h7V7h-7V5h7V3h-7V1zM9.088 9h-4.18L3.096 29l.058.002L10.906 29l-.004-.058zM17 3h-2v6h2zM9.002 3H5L5 7h4.004z\"></path></svg>")
                                .description("Khăn tắm, khăn trải giường, xà phòng và giấy vệ sinh")
                                .amentityCategory(saved).build();

                repository.save(amentity);

                assertThat(amentity.getId()).isPositive();
        }

        @Test
        void testDeleteAmentity() {
                repository.deleteById(2);
        }

        @Test
        void testUpdateAmentityStatus() {
                Amentity amentity = repository.findById(1).get();
                amentity.setStatus(true);
                repository.save(amentity);

                assertThat(amentity.isStatus()).isTrue();
        }

        @Test
        void testAddMultipleAmentit2y() {
                Amentity amentity = Amentity.builder().name("Chỗ đỗ xe miễn phí ở nơi ở")
                                .iconImage("free_parking_lot.svg").build();
                Amentity amentity2 = Amentity.builder().name("Chỗ đỗ xe có thu phí trong khuôn viên")
                                .iconImage("non_free_parking_lot.svg").build();
                Amentity amentity3 = Amentity.builder().name("Không gian riêng để làm việc")
                                .iconImage("private_space_for_work.svg").build();
                Amentity amentity4 = Amentity.builder().name("Vòi sen tắm ngoài trời")
                                .iconImage("voi_sen_tam_ngoai_troi.svg").build();

                repository.saveAll(List.of(amentity, amentity2, amentity3, amentity4));
        }

}