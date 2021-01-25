import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { WbaHipsterTestModule } from '../../../test.module';
import { LineInfoComponent } from 'app/entities/line-info/line-info.component';
import { LineInfoService } from 'app/entities/line-info/line-info.service';
import { LineInfo } from 'app/shared/model/line-info.model';

describe('Component Tests', () => {
  describe('LineInfo Management Component', () => {
    let comp: LineInfoComponent;
    let fixture: ComponentFixture<LineInfoComponent>;
    let service: LineInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WbaHipsterTestModule],
        declarations: [LineInfoComponent],
      })
        .overrideTemplate(LineInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LineInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LineInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LineInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.lineInfos && comp.lineInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
