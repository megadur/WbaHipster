import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { WbaHipsterTestModule } from '../../../test.module';
import { BngInfoComponent } from 'app/entities/bng-info/bng-info.component';
import { BngInfoService } from 'app/entities/bng-info/bng-info.service';
import { BngInfo } from 'app/shared/model/bng-info.model';

describe('Component Tests', () => {
  describe('BngInfo Management Component', () => {
    let comp: BngInfoComponent;
    let fixture: ComponentFixture<BngInfoComponent>;
    let service: BngInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WbaHipsterTestModule],
        declarations: [BngInfoComponent],
      })
        .overrideTemplate(BngInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BngInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BngInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BngInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.bngInfos && comp.bngInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
