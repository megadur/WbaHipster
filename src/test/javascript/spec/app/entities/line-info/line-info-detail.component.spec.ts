import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WbaHipsterTestModule } from '../../../test.module';
import { LineInfoDetailComponent } from 'app/entities/line-info/line-info-detail.component';
import { LineInfo } from 'app/shared/model/line-info.model';

describe('Component Tests', () => {
  describe('LineInfo Management Detail Component', () => {
    let comp: LineInfoDetailComponent;
    let fixture: ComponentFixture<LineInfoDetailComponent>;
    const route = ({ data: of({ lineInfo: new LineInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WbaHipsterTestModule],
        declarations: [LineInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(LineInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LineInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load lineInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.lineInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
