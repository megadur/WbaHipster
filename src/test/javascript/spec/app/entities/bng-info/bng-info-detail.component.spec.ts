import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WbaHipsterTestModule } from '../../../test.module';
import { BngInfoDetailComponent } from 'app/entities/bng-info/bng-info-detail.component';
import { BngInfo } from 'app/shared/model/bng-info.model';

describe('Component Tests', () => {
  describe('BngInfo Management Detail Component', () => {
    let comp: BngInfoDetailComponent;
    let fixture: ComponentFixture<BngInfoDetailComponent>;
    const route = ({ data: of({ bngInfo: new BngInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WbaHipsterTestModule],
        declarations: [BngInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BngInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BngInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bngInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bngInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
